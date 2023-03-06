package com.biaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/ko")
public class MemberController {
	@GetMapping(value="/memberedit")
	public String memberedit() {
		return "/member/memberedit";
	}
	
	@GetMapping(value="/memberout")
	public String memberout() {
		return "/member/memberout";
	}
	
	@GetMapping(value="/memberout1")
	public String memberout1() {
		return "/member/memberout1";
	}
	
	
}
