package com.biaf.controller;


import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biaf.dto.MemberFormDto;
import com.biaf.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value="/ko")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	
	@GetMapping(value="/mypage/{memberId}") //마이페이지 정보페이지
	public String mypage(@AuthenticationPrincipal User user, Model model, HttpServletRequest request) {
		HttpSession mySession = request.getSession();
		String myemail = (String)mySession.getAttribute("email");
		MemberFormDto memFormDto = memberService.mypagefindByMemberEmail(user.getUsername());
	
		if(memFormDto == null) {
			memFormDto = new MemberFormDto();
			memFormDto.setMemberEmail(myemail);
		}
		model.addAttribute("memberFormDto", memFormDto);
		return "/member/mypage";
	}
	
	@GetMapping(value="/myedit") //회원정보 수정
	public String myedit() {
		
		return "/member/myedit";
	}
	

	@GetMapping(value="/memberout") //회원탈퇴 
	public String memberout() {

		return "/member/memberout";
	}
	
	@DeleteMapping(value="/memberout/delete") //회원탈퇴 
	public String memberdelete(Principal principal) {
		memberService.deletemember(principal.getName());
		
		return "redirect:/ko/memberout1";
	}
	

	@GetMapping(value="/memberout1") //회원탈퇴 완료
	public String memberout1() {
		return "/member/memberout1";
	}
	@GetMapping(value="/cart") //장바구니
	public String cart() {
		return "/member/cart";
	}
	
	
}
