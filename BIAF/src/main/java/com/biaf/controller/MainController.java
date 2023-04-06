package com.biaf.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.biaf.dto.NoticeBoardDto;
import com.biaf.service.NoticeBoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final NoticeBoardService NoticeBoardservice;

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
		return "/main/MainPage";
	}
	@GetMapping(value="/ko/search")
	public String search(){
		return "/main/Search";
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
