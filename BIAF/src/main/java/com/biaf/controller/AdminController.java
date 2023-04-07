package com.biaf.controller;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.biaf.dto.MovieFormDto;
import com.biaf.entity.Member;
import com.biaf.entity.Movie;
import com.biaf.service.MemberService;
import com.biaf.service.MovieService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/ko")
@EnableAsync
public class AdminController {
	private final MemberService memberService;
	private final MovieService movieService;

	
	@GetMapping(value = "/adminpage") // 관리자페이지 기본틀 -삭제할거임
	public String adminpage() {
		return "/admin/adminpage";
	}

	@GetMapping(value="/memberList") //회원 조회
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
		
		return "admin/memberList";
	}
	@DeleteMapping(value="/memdelete") //회원삭제
	public String memDelete(@RequestParam("id") Long id) {
	 	memberService.memDelete(id);
	 	return "redirect:/ko/memberList";
	}

	@GetMapping(value = "/movieForm") // 영화등록 폼
	public String movieForm(Model model) {
		model.addAttribute("movieFormDto", new MovieFormDto());
		return "/admin/movieForm";
	}

	@PostMapping(value = "/movieForm")
	public String movieNew(@Valid MovieFormDto movieFormDto, BindingResult bindingResult, Model model,
			@RequestParam("movieImgFile") MultipartFile movieImgFileList) {
		if (bindingResult.hasErrors()) { // 영화 등록시 필수 값이 없다면 다시 영화 등록 페이지로 전환한다.
			return "/admin/movieForm";
		}

		if (movieImgFileList.isEmpty() && movieFormDto.getId() == null) {
			model.addAttribute("errorMessage", "영화 이미지는 필수 입력 값 입니다.");
			return "/admin/movieForm"; // 영화 등록시 이미지가 없다면 에러 메시지와 함께 영화등록 페이지로 전환한다.
		}

		try {
			movieService.saveMovie(movieFormDto, movieImgFileList); // 영화 저장 로직을 호출. 영화정보와 영화이미지정보를 넘긴다.
		} catch (Exception e) {
			model.addAttribute("errorMessage", "영화 등록 중 에러가 발생하였습니다.");
			return "/admin/movieForm";
		}

		return "redirect:/ko/movieMng"; // 정상적으로 등록되었다면 영화등록리스트로 이동한다.
	}

	@GetMapping(value = "/movieMng") // 영화등록관리(영화리스트)
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
	

//	// 영화 수정
//	@GetMapping(value = "/admin/movie/{movieId}") // url 경로 변수는 { } 표현
//	public String movieDtl(@PathVariable("movieId") Long movieId, Model model) {
//
//		try {
//			MovieFormDto movieFormDto = movieService.getMovieDtl(movieId); // 조회한 상품 데이터를 모델에 담아 뷰로 전달한다.
//			model.addAttribute("movieFormDto", movieFormDto);
//		} catch (EntityNotFoundException e) { // 영화 엔티티가 존재하지 않을 경우 에러 메시지를 담아 영화 등록 페이지로 이동한다.
//			model.addAttribute("errorMessage", "존재하지 않는 영화 입니다.");
//			model.addAttribute("movieFormDto", new MovieFormDto());
//			return "movie/movieForm";
//		}
//
//		return "/admin/movieForm";
//	}
//
//	@PostMapping(value = "/admin/movie/{movieId}")
//	public String movieUpdate(@Valid MovieFormDto movieFormDto, BindingResult bindingResult,
//			@RequestParam("movieImgFile") MultipartFile movieImgFileList, Model model) {
//		if (bindingResult.hasErrors()) {
//			return "movie/movieForm";
//		}
//
//		if (movieImgFileList.isEmpty() && movieFormDto.getId() == null) {
//			model.addAttribute("errorMessage", "영화 이미지는 필수 입력 값 입니다.");
//			return "movie/movieForm";
//		}
//
//		try {
//			movieService.updateMovie(movieFormDto, movieImgFileList);
//		} catch (Exception e) {
//			model.addAttribute("errorMessage", "영화 수정 중 에러가 발생하였습니다.");
//			return "movie/movieForm";
//		}
//
//		return "redirect:/ko/movieMng";
//	}

	@GetMapping(value = "/reservationadmin") // 예매관리
	public String reservationadmin() {
		return "/admin/reservationadmin";
	}
}