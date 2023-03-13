package com.biaf.controller;

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

import com.biaf.dto.MemberFormDto;
import com.biaf.entity.Member;
import com.biaf.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value="/ko")
@RequiredArgsConstructor
public class LoginController {
	

	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder; 
	
	@GetMapping(value="/login")
	public String login() {
		return "login/memberlogin";
	}

	@GetMapping(value="/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 확인해주세요");
		return "login/memberlogin";
	}
	@PostMapping(value="/login")
	public String loginForm() {
		return "login/memberlogin";
	}
	
	@GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
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
	
	@GetMapping(value="/pwfind")//비밀번호 찾기
	public String pwfind() {
		return "/login/pwfind";
	}
	
	@GetMapping(value="/pwreset") // 비밀번호 리셋
	public String pwreset() {
		return "/login/pwreset";
	}
	
	
    
    
    
}
