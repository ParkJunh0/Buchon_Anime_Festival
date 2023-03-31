package com.biaf.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.biaf.dto.MovieFormDto;
import com.biaf.entity.Movie;
import com.biaf.service.MemberService;
import com.biaf.service.MovieService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/ko")
public class MovieController {
    private final MemberService memberService;
	private final MovieService movieService;

    @GetMapping(value = "/admin/movieForm") // 영화등록 폼
	public String movieForm(Model model) {
		model.addAttribute("movieFormDto", new MovieFormDto());
		return "/admin/movieForm";
	}

    @PostMapping(value = "/admin/movieForm")
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

		return "redirect:/ko/admin/movie"; // 정상적으로 등록되었다면 영화등록리스트로 이동한다.
	}
    // 영화 수정
	@GetMapping(value = "/admin/movie/{movieId}") // url 경로 변수는 { } 표현
	public String movieDtl(@PathVariable("movieId") Long movieId, Model model) {

		try {
			MovieFormDto movieFormDto = movieService.getMovieDtl(movieId); // 조회한 상품 데이터를 모델에 담아 뷰로 전달한다.
			model.addAttribute("movieFormDto", movieFormDto);
		} catch (EntityNotFoundException e) { // 영화 엔티티가 존재하지 않을 경우 에러 메시지를 담아 영화 등록 페이지로 이동한다.
			model.addAttribute("errorMessage", "존재하지 않는 영화 입니다.");
			model.addAttribute("movieFormDto", new MovieFormDto());
			return "movie/movieForm";
		}

		return "/admin/movieForm";
	}
    @PostMapping(value = "/admin/movie/{movieId}")
	public String movieUpdate(@Valid MovieFormDto movieFormDto, BindingResult bindingResult,
			@RequestParam("movieImgFile") MultipartFile movieImgFileList, Model model) {
		if (bindingResult.hasErrors()) {
			return "movie/movieForm";
		}

		if (movieImgFileList.isEmpty() && movieFormDto.getId() == null) {
			model.addAttribute("errorMessage", "영화 이미지는 필수 입력 값 입니다.");
			return "movie/movieForm";
		}
		

		try {
			movieService.updateMovie(movieFormDto, movieImgFileList);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "영화 수정 중 에러가 발생하였습니다.");
			return "movie/movieForm";
		}

		return "redirect:/ko/admin/movie";
	}
    // 영화 리스트 삭제
	@PostMapping(value = "/admin/movie/delete/{movieId}")
	public String moviedelete(@PathVariable Long movieId, @RequestParam("movieImgIds") Long imgId) {
		movieService.deletemovie(movieId, imgId);
		return "redirect:/ko/admin/movie"
				+ ""
				+ "";
	}
}
