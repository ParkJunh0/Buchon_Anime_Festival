package com.biaf.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biaf.dto.BannerFormDto;
import com.biaf.entity.Banner;
import com.biaf.entity.Member;
import com.biaf.entity.Movie;
import com.biaf.service.BannerService;
import com.biaf.service.MemberService;
import com.biaf.service.MovieService;
import com.biaf.dto.ReservationFormDto;
import com.biaf.entity.Reservation;
import com.biaf.service.ReservationService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/ko/admin")
public class AdminController {
	private final MemberService memberService;
	private final MovieService movieService;
	private final BannerService bannerservice;
	private final ReservationService reservationService;

	@GetMapping(value = { "", "/memberList" }) // 회원 조회
	public String memberList(Model model,
			@PageableDefault(page = 0, size = 5, sort = "memberId", direction = Sort.Direction.ASC) Pageable pageable) {
		Page<Member> list = memberService.memList(pageable);
		model.addAttribute("memberResponseDto", list);

		int nowPage = list.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 9, list.getTotalPages());

		model.addAttribute("list", list);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "/admin/memberList";
	}

	@DeleteMapping(value = "/memdelete") // 회원삭제
	public String memDelete(@RequestParam("id") Long id) { // @RequestParam("id")-html에서 받아온 값 Long id 여기에 저장
		memberService.memDelete(id); // memberService안에 있는 memDelete(id)를 호출
		return "redirect:/ko/admin/memberList";
	}

	@GetMapping(value = "/movie") // 영화등록관리(영화리스트)
	public String movieMng(Model mvmodel,
			@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable mvpageable) {

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
	public String reservationadmin(Model resmodel,
			@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

		Page<Reservation> reser = reservationService.reser(pageable);
		resmodel.addAttribute("reservationFormDto", reser);
		int resnowPage = reser.getPageable().getPageNumber() + 1; // 페이징
		int resstartPage = Math.max(resnowPage - 4, 1);
		int resendPage = Math.min(resnowPage + 9, reser.getTotalPages());

		resmodel.addAttribute("reslist", reser);
		resmodel.addAttribute("nowPage", resnowPage);
		resmodel.addAttribute("startPage", resstartPage);
		resmodel.addAttribute("endPage", resendPage);

		return "admin/reservationadmin";
	}

	@GetMapping(value = "/banner") // 베너관리
	public String banner(Model model,
			@PageableDefault(page = 0, size = 3, sort = "bannerDate", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Banner> bannerlist = bannerservice.bannerlist(pageable);

		int nowPage = bannerlist.getPageable().getPageNumber() + 1; // 페이징
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 9, bannerlist.getTotalPages());

		model.addAttribute("bannerList", bannerlist);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "/admin/bannerList";
	}

	@GetMapping(value = "/banner/{id}") // url 경로 변수는 { } 표현한다.
	public String goodsDtl(@PathVariable("id") Long bannerId, Model model) {
		try {
			BannerFormDto bannerFormDto = bannerservice.getbanner(bannerId); // 조회한 배너 데이터를 모델에 담아 뷰로 전달한다.
			model.addAttribute("BannerFormDto", bannerFormDto);

		} catch (EntityNotFoundException e) { // 상품 엔티티가 존재하지 않을 경우 에러 메시지를 담아 배너 등록 페이지로 이동한다.
			model.addAttribute("errorMessage", "존재하지 배너 입니다.");
			model.addAttribute("BannerFormDto", new BannerFormDto());
			return "/admin/bannerForm";
		}
		return "/admin/bannerForm";
	}

	@GetMapping(value = "/bannerForm")
	public String bannerForm(Model model) {
		model.addAttribute("BannerFormDto", new BannerFormDto());
		return "/admin/bannerForm";
	}

	@PostMapping(value = "/banner/new")
	public String bannersave(@Valid BannerFormDto bannerFormDto,
			@RequestParam("bannerImgFile") MultipartFile bannerImgFile, BindingResult bindingResult, Model model,
			RedirectAttributes rmodel) {
		if (bannerImgFile.isEmpty() && bannerFormDto.getId() == null) {
			rmodel.addFlashAttribute("errorMessage", "배너로 넣을 이미지를 선택해주세요");
			return "redirect:/ko/admin/bannerForm"; // 상품 등록시 첫 번째 이미지가 없다면 에러 메시지와 함께 상품등록 페이지로 전환한다.
		} // 상품 첫번째 이미지는 메인 페이지에서 보여줄 상품 이미지를 사용하기 위해 필수 값으로 지정한다.
		try {
			bannerservice.bannercreate(bannerFormDto, bannerImgFile);
		} catch (Exception e) {
			rmodel.addFlashAttribute("errorMessage", "배너등록 중 에러가 발생하였습니다.");
			return "redirect:/ko/admin/bannerForm";
		}
		return "redirect:/ko/admin/banner";
	}

	// 배너 삭제
	@DeleteMapping(value = "/banner/delete/{id}")
	public String noticedelete(@PathVariable Long id) {

		bannerservice.bannerdelete(id); // bannerService에 있는 bannerdelete를 호출
		return "redirect:/ko/admin/banner";
	}
}
