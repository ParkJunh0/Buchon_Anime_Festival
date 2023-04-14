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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.biaf.dto.BannerFormDto;
import com.biaf.dto.GoodsFormDto;
import com.biaf.entity.Banner;
import com.biaf.entity.Member;
import com.biaf.entity.Movie;
import com.biaf.service.BannerService;
import com.biaf.service.MemberService;
import com.biaf.service.MovieService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/ko/admin")
public class AdminController {
	private final MemberService memberService;
	private final MovieService movieService;
	private final BannerService bannerservice;
	
	@GetMapping(value = {"", "/memberList"}) // 회원 조회
	public String memberList(Model model,
			@PageableDefault(page = 0, size = 5, sort="memberId", direction = Sort.Direction.ASC) Pageable pageable) {
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
			@PageableDefault(page = 0, size = 5, direction = Sort.Direction.DESC) Pageable mvpageable) {
					
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
	public String reservationadmin() {
		return "/admin/reservationadmin";
	}
	
	@GetMapping(value="/banner") // 베너관리
	public String banner(Model model, @PageableDefault(page=0, size=5, sort="bannerDate", direction=Sort.Direction.DESC) Pageable pageable){
		Page<Banner> bannerlist = bannerservice.bannerlist(pageable);

		int nowPage = bannerlist.getPageable().getPageNumber() + 1;    //페이징   
        int startPage =  Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage+9, bannerlist.getTotalPages());

		model.addAttribute("bannerList", bannerlist);
		model.addAttribute("nowPage",nowPage);
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

	@GetMapping(value="/bannerForm")
	public String bannerForm(Model model){
		model.addAttribute("BannerFormDto", new BannerFormDto());
		return "/admin/bannerForm";
	}

	@PostMapping(value="/banner/new")
	public String bannersave(@Valid BannerFormDto bannerFormDto, @RequestParam("bannerImgFile") MultipartFile bannerImgFile, BindingResult bindingResult, Model model){
		if (bindingResult.hasErrors()) { // 상품 등록시 필수 값이 없다면 다시 상품 등록 페이지로 전환한다.
            return "admin/bannerForm";
        }
		if (bannerImgFile.isEmpty() && bannerFormDto.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "admin/bannerForm"; // 상품 등록시 첫 번째 이미지가 없다면 에러 메시지와 함께 상품등록 페이지로 전환한다.
        } // 상품 첫번째 이미지는 메인 페이지에서 보여줄 상품 이미지를 사용하기 위해 필수 값으로 지정한다.
		try{
		bannerservice.bannercreate(bannerFormDto, bannerImgFile);
		}catch(Exception e){
			model.addAttribute("errorMessage", "배너등록 중 에러가 발생하였습니다.");
			return "admin/bannerForm";
		}
		return "redirect:/ko/admin/banner";
	}

	//배너 삭제 
	@DeleteMapping(value="/banner/delete/{id}")
	public String noticedelete(@PathVariable Long id){
   	
		bannerservice.bannerdelete(id); //bannerService에 있는 bannerdelete를 호출
   		return "redirect:/ko/admin/banner";
	}
}
