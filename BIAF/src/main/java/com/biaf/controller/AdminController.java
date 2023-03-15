package com.biaf.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.biaf.dto.MovieFormDto;
import com.biaf.service.MemberService;
import com.biaf.service.MovieService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/ko")
public class AdminController {
	private final MemberService memberService;
	private final MovieService movieService;
	
	@GetMapping(value="/adminpage") //관리자페이지 기본틀 -삭제할거임
	public String adminpage() {
		return "/admin/adminpage";
	}
	@GetMapping(value="/memberList") //회원 조회
	public String memberList(Model model) {
		model.addAttribute("memberResponseDto", memberService.findAll());
		return "/admin/memberList";
	}
	@GetMapping(value="/movieForm") //영화등록관리
	public String movieForm(Model model) {
		model.addAttribute("movieFormDto", new MovieFormDto());
		return "/admin/movieForm";
	}
	

	@PostMapping(value = "/movieForm")
	public String movieNew(@Valid MovieFormDto movieFormDto, BindingResult bindingResult,
		Model model, @RequestParam("movieImgFile") MultipartFile movieImgFileList){
		if(bindingResult.hasErrors()){ // 영화 등록시 필수 값이 없다면 다시 영화 등록 페이지로 전환한다.
	return "/admin/movieForm";
	}
		
	if(movieImgFileList.isEmpty() && movieFormDto.getId() == null){
		model.addAttribute("errorMessage", "영화 이미지는 필수 입력 값 입니다.");
		return "/admin/movieForm"; // 상품 등록시 이미지가 없다면 에러 메시지와 함께 영화등록 페이지로 전환한다.
	} 
	
	try {
		movieService.saveMovie(movieFormDto, movieImgFileList); // 상품 저장 로직을 호출. 상품정보와 상품이미지정보를 넘긴다.
	} catch (Exception e){
		model.addAttribute("errorMessage", "영화 등록 중 에러가 발생하였습니다.");
		return "/admin/movieForm";
	}
	
	return "redirect:/ko/schedule"; // 정상적으로 등록되었다면 메인페이지로 이동한다.
	}
	
	
	@GetMapping(value="/reservationadmin") //예매관리
	public String reservationadmin() {
		return "/admin/reservationadmin";
	}
}
