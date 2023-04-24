package com.biaf.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.biaf.dto.BannerFormDto;
import com.biaf.dto.FreeBoardDto;
import com.biaf.dto.MovieResponseDto;
import com.biaf.dto.NoticeBoardDto;
import com.biaf.service.BannerService;
import com.biaf.service.FreeBoardService;
import com.biaf.service.MovieService;
import com.biaf.service.NoticeBoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	private final MovieService movieService;
	private final NoticeBoardService NoticeBoardservice;
	private final BannerService bannerservice;
	private final FreeBoardService freeboardservice;

	@GetMapping(value="/ko/example")
	public String example(){
		return "Example";
	}
	@GetMapping(value="/")
	public String firstpage() {
		return "/main/FirstPage";
	}
	@GetMapping(value="/ko")
	public String main(Model model) {
		// 배너 부분
		List<BannerFormDto> bannerlist = bannerservice.alllist();
		model.addAttribute("bannerlist", bannerlist);

		// 영화 목록
		List<MovieResponseDto> movie = movieService.findAll();
		model.addAttribute("movielist", movie);

		// 공지사항 게시글
		List<NoticeBoardDto> notice = new ArrayList<NoticeBoardDto>();
		List<NoticeBoardDto> ins = NoticeBoardservice.mainboardlist();
		NoticeBoardDto noticeone = new NoticeBoardDto();
		if(ins.size() != 0){
		noticeone = ins.get(0);

		String ldate = String.valueOf(noticeone.getRegTime());
		String ldatet = ldate.substring(0, 7);
		String ldateb = ldate.substring(8, 10);

		model.addAttribute("last_notice", noticeone);
		model.addAttribute("last_datet", ldatet);
		model.addAttribute("last_dateb", ldateb);
		
		if(ins.size() > 1){
			for(int i=1; i < ins.size() && i < 7; i++){
				notice.add(ins.get(i));
			}
			model.addAttribute("noticeDto", notice);
		}else{
			model.addAttribute("notnotice", 1);
		}
		}else{
			model.addAttribute("lnotnotice", 1);
			model.addAttribute("notnotice", 1);
		}

		// 자유게시판 게시글
		List<FreeBoardDto> freeboard = new ArrayList<>();
		List<FreeBoardDto> free = freeboardservice.mainboardlist();
		FreeBoardDto freeone = new FreeBoardDto();
		if(free.size() != 0){
		freeone = free.get(0);

		String lfdate = String.valueOf(freeone.getRegTime());
		String lfdatet = lfdate.substring(0, 7);
		String lfdateb = lfdate.substring(8, 10);

		model.addAttribute("last_free", freeone);
		model.addAttribute("last_fdatet", lfdatet);
		model.addAttribute("last_fdateb", lfdateb);
		
		if(ins.size() > 1){
			for(int i=1; i < free.size() && i < 7; i++){
				freeboard.add(free.get(i));
			}
			model.addAttribute("freeDto", freeboard);
		}else{
			model.addAttribute("notfree", 1);
		}
		}else{
			model.addAttribute("lnotfree", 1);
			model.addAttribute("notfree", 1);
		}
		return "/main/MainPage";
	}
	
   // 메인페이지 용 맵핑
   @GetMapping(value="/header.html")
   public String header(){
      return "/fragments/header";
   }
   @GetMapping(value="/footer.html")
   public String footer(){
      return "/fragments/footer";
   }
   @GetMapping(value="/quickmenu.html")
   public String quickmenu(){
      return "/fragments/quickmenu";
   }
}
