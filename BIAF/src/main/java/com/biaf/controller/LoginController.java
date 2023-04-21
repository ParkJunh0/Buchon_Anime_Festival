package com.biaf.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biaf.constant.Role;
import com.biaf.dto.MailDto;
import com.biaf.dto.MemberFormDto;
import com.biaf.entity.Member;
import com.biaf.service.MailService;
import com.biaf.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value="/ko")
@RequiredArgsConstructor
public class LoginController {
	private final MailService mailservice;
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder; 
	
	@PostConstruct
	   //계정 생성
	   private void createAdmin() {
	      //관리자
		boolean check = memberService.findById("admin@test.com");
		if(check)
			return;
	      MemberFormDto memberFormDto = new MemberFormDto();
	      memberFormDto.setMemberEmail("admin@test.com");
	      memberFormDto.setMemberPassword("123123123");
	      memberFormDto.setMemberName("관리자");
	      memberFormDto.setMemberTel("010-0000-0000");
	     
	      
	      Member member = Member.createMember(memberFormDto , passwordEncoder);
	      String password = passwordEncoder.encode(memberFormDto.getMemberPassword());
	      member.setMemberPassword(password);
	      member.setRole(Role.ADMIN);
	      memberService.saveMember(member);
	    
		for(int i=1; i<10; i++) { //회원생성
			check = memberService.findById("test"+i+"@test.com");
			if(check)
				return;
			
		      memberFormDto.setMemberEmail("test"+i+"@test.com");
		      memberFormDto.setMemberPassword("123123123");
		      memberFormDto.setMemberName("테스트"+i);
		      memberFormDto.setMemberTel("010-0000-000"+i);
		      member = Member.createMember(memberFormDto, passwordEncoder);
		      password = passwordEncoder.encode(memberFormDto.getMemberPassword());
		      member.setMemberPassword(password);
		      member.setRole(Role.USER);
		      memberService.saveMember(member);
			}
		}
		
	@GetMapping(value="/login")
	public String login() {
		return "login/memberlogin";
	}

	@GetMapping(value="/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
		return "login/memberlogin";
	}

	@PostMapping(value="/login")
	public String loginForm() {
		return "login/memberlogin";
	}
	
	@GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, 
			SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/ko";
    }
	
    @GetMapping(value="/signup")
    public String signupForm(Model model) {
    	model.addAttribute("memberFormDto",new MemberFormDto());
    	return "login/signup";
    }
	
	  @PostMapping(value ="/signup")
	  public String singupForm(@Valid MemberFormDto memberFormDto,BindingResult bindingResult, Model model) {
	 
	  if(bindingResult.hasErrors()){ 
		  	return "login/signup"; 
	} 
	   try { 
	   Member member =Member.createMember(memberFormDto, passwordEncoder);
	   memberService.saveMember(member);
	  
	  } catch(IllegalStateException e) {
	  model.addAttribute("errorMessage",e.getMessage());
	  return "login/signup";
	  
	  }
	  return "redirect:/ko/join"; 
	  }
	 
    @GetMapping(value="/terms") // 약관동의
	public String terms() {
		return "/login/terms";
	}
    @GetMapping(value="/join")//회원가입 완료
	public String join() {
		return "/login/join";
	}
	
	@GetMapping(value="/idfind") // 아이디찾기
	public String idfind() {
		return "/login/idfind";
	}
	
	@RequestMapping(value = "/findId", method = RequestMethod.POST)
	@ResponseBody		//requestparam("가져올데이터이름") 데이터타입 변수이름 
	public String findId(@RequestParam("memName") String memberName,@RequestParam("memTel") String memberTel) {

		String findEmail = memberService.findEmail(memberName, memberTel);
		if(findEmail==null || findEmail == "") {
			return null; 
		}else {
			return findEmail;
		}
		
	}

	@GetMapping(value="/pwfind")//비밀번호 찾기
	public String pwfind() {
		return "/login/pwfind";
	}
	
	@GetMapping("/check/findPw")
    public @ResponseBody Map<String, Boolean> pw_find(String email, String memberName){
        Map<String,Boolean> json = new HashMap<>();
        boolean pwFindCheck = memberService.memberEmailCheck(email,memberName);

        System.out.println(pwFindCheck);
        json.put("check", pwFindCheck);
        return json;
    }
	
	
	 @PostMapping("/check/findPw/sendEmail")
	    public @ResponseBody void sendEmail(String email, String memberName){
		
		 
	        MailDto dto = mailservice.createMailAndChangePassword(email, memberName, passwordEncoder);
	        mailservice.mailSend(dto);
	}
	
}
