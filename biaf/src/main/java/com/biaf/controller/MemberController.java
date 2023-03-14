package com.biaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value="/ko")
@RequiredArgsConstructor
public class MemberController {
	@GetMapping(value="/memberedit") //마이페이지 수정
	public String memberedit() {
		return "/member/memberedit";
	}
	
	@GetMapping(value="/memberout") //회원탈퇴
	public String memberout() {
		return "/member/memberout";
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
