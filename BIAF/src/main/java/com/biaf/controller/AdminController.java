package com.biaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/ko")
public class AdminController {
	@GetMapping(value="/adminpage") //관리자페이지 기본틀 -삭제할거임
	public String adminpage() {
		return "/admin/adminpage";
	}
	@GetMapping(value="/memberList") //회원 조회
	public String memberList() {
		return "/admin/memberList";
	}
	@GetMapping(value="/movie_reg") //영화등록관리
	public String movie_reg() {
		return "/admin/movie_reg";
	}
	@GetMapping(value="/reservationadmin") //예매관리
	public String reservationadmin() {
		return "/admin/reservationadmin";
	}
}
