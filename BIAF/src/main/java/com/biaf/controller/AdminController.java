package com.biaf.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biaf.entity.Member;
import com.biaf.entity.Movie;
import com.biaf.service.MemberService;
import com.biaf.service.MovieService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/ko/admin")
public class AdminController {
	private final MemberService memberService;
	private final MovieService movieService;
	
	@GetMapping(value="") //관리자페이지 기본틀 -삭제할거임
	public String adminpage() {
		return "/admin/adminpage";
	}
	
	@GetMapping(value = "/memberList") // 회원 조회
	public String memberList(Model model, @PageableDefault(page=0, size=5, direction=Sort.Direction.DESC) Pageable pageable) {

	      Page<Member> list = memberService.memList(pageable);
	      model.addAttribute("memberResponseDto", list);
	              
	        int nowPage = list.getPageable().getPageNumber() + 1;    //페이징   
	        int startPage =  Math.max(nowPage - 4, 1);
	        int endPage = Math.min(nowPage+9, list.getTotalPages());

	        model.addAttribute("list", list);
	        model.addAttribute("nowPage",nowPage);
	        model.addAttribute("startPage", startPage);
	        model.addAttribute("endPage", endPage);
			return "/admin/memberList";
		}
	@DeleteMapping(value="/memdelete") //회원삭제
	public String memDelete(@RequestParam("id") Long id) {
			memberService.memDelete(id);
			return "redirect:/ko/admin/memberList";
	}
	@GetMapping(value = "/movie") // 영화등록관리(영화리스트)
	public String movieMng(Model mvmodel, @PageableDefault(page=0, size=5, direction=Sort.Direction.DESC) Pageable mvpageable) {
		
		Page<Movie> mvlist = movieService.mvList(mvpageable);
		mvmodel.addAttribute("movieResponseDto", mvlist);
		
		
		  	int mvnowPage = mvlist.getPageable().getPageNumber() + 1;    //페이징   
	        int mvstartPage =  Math.max(mvnowPage - 4, 1);
	        int mvendPage = Math.min(mvnowPage+9, mvlist.getTotalPages());

	        mvmodel.addAttribute("mvlist", mvlist);
	        mvmodel.addAttribute("nowPage",mvnowPage);
	        mvmodel.addAttribute("startPage", mvstartPage);
	        mvmodel.addAttribute("endPage", mvendPage);
	        
		return "/admin/movieMng";
	}
	
	@GetMapping(value = "/reservationadmin") // 예매관리
	public String reservationadmin() {
		return "/admin/reservationadmin";
	}
}
