package com.biaf.controller;

import java.util.List;

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

import com.biaf.dto.ReservationFormDto;
import com.biaf.entity.Member;
import com.biaf.entity.Movie;
import com.biaf.service.MemberService;
import com.biaf.service.MovieService;
import com.biaf.service.ReservationService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/ko/admin")
public class AdminController {
	private final MemberService memberService;
	private final MovieService movieService;
	private final ReservationService reservationService;
	
	@GetMapping(value = {"", "/memberList"}) // 회원 조회
	public String memberList(Model model,
			@PageableDefault(page = 0, size = 5, sort="memberId",  direction = Sort.Direction.DESC) Pageable pageable) {
//@PageableDefault - 페이지기능   한페이지에 5개씩보여줌 sort정렬 desc내림차순정렬 asc-디폴드값 오름차순
		Page<Member> list = memberService.memList(pageable);
		model.addAttribute("memberResponseDto", list); // memberResponseDto(이름 내맘대로)에 list바인딩(저장)

		int nowPage = list.getPageable().getPageNumber() + 1; // 페이징 0부터시작이라 +1
		int startPage = Math.max(nowPage - 4, 1); // nowPage - 4 화살표기능 넣으려고
		int endPage = Math.min(nowPage + 9, list.getTotalPages());

		model.addAttribute("list", list);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "/admin/memberList";
	}

	@DeleteMapping(value="/memdelete") //회원삭제
	public String memDelete(@RequestParam("id") Long id) { // @RequestParam("id")-html에서 받아온 값 Long id 여기에 저장
		memberService.memDelete(id); // memberService안에 있는 memDelete(id)를 호출
			return "redirect:/ko/admin/memberList";
	}

	@GetMapping(value = "/movie") // 영화등록관리(영화리스트)
	public String movieMng(Model mvmodel,
			@PageableDefault(page = 0, size = 5, sort = "id" , direction = Sort.Direction.ASC) Pageable mvpageable) {
					
		Page<Movie> mvlist = movieService.mvList(mvpageable);
		mvmodel.addAttribute("movieResponseDto", mvlist);
		
		int mvnowPage = mvlist.getPageable().getPageNumber() + 1; // 페이징
		int mvstartPage = Math.max(mvnowPage - 4, 1);
		int mvendPage = Math.min(mvnowPage + 9, mvlist.getTotalPages());

		mvmodel.addAttribute("mvlist", mvlist);
		mvmodel.addAttribute("nowPage", mvnowPage);
		mvmodel.addAttribute("startPage", mvstartPage);
		mvmodel.addAttribute("endPage", mvendPage);
		
		return "/admin/movieMng";
	}
	
	@GetMapping(value = "/reservationadmin") // 예매관리
	public String reservationadmin(Model model) {
		List<ReservationFormDto> reserformDto = reservationService.findAll();
	    model.addAttribute("reservationFormDto", reserformDto);
		return "/admin/reservationadmin";
	}

}



