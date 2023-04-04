package com.biaf.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.biaf.dto.NoticeBoardDto;
import com.biaf.dto.NoticeBoardFormDto;
import com.biaf.entity.NoticeBoard;
import com.biaf.service.NoticeBoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/ko")
public class BoardController {
	private final NoticeBoardService noticeboardservice;
	
	//공지사항 페이지
    @GetMapping(value="/notice")
    public String notice(Model model, @PageableDefault(page=0, size=5, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
        Page<NoticeBoard> list = noticeboardservice.boardList(pageable);
        
        int nowPage = list.getPageable().getPageNumber() + 1;    //페이징   
        int startPage =  Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage+9, list.getTotalPages());
    	model.addAttribute("noticeList", noticeboardservice.boardList(pageable));
    	model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        
        return "/Board/Notice/notice";
    }
    
    //공지사항 상세 페이지
    @GetMapping(value="/notice/detail/{id}")
	public String noticedetail(@PathVariable("id") Long noticeBoardId, Model model){
    	NoticeBoardFormDto noticeBoardFormDto = noticeboardservice.getNoticeBoardDtl(noticeBoardId); // noticeBoardFormDto 안에 boardService.getNoticeBoardDtl(noticeBoardId); 값을 대입 
    	model.addAttribute("noticeBoardFormDto", noticeBoardFormDto); //model.addAttribute에  noticeBoardFormDto의 이름을 noticeBoardFormDto로 add

        
		return "/Board/Notice/noticedetail";
	}
    
    //공지사항 작성 페이지
   	@GetMapping(value="/notice/form")
	public String noticeform(Model model, NoticeBoardFormDto noticeBoardFormDto){
//   		model.addAttribute("noticeBoardDto", new NoticeBoardDto());
   		model.addAttribute("noticeBoardFormDto", new NoticeBoardFormDto());  //new NoticeBoardFormDto()를 생성후 noticeBoardFormDto를 add
   		
   		
		
		return "/Board/Notice/noticeform";
	}
   	//공지사항 작성 적용
   	@PostMapping(value="/notice/write")
    public String noticewrite(@Valid NoticeBoardFormDto noticeBoardFormDto, BindingResult bindingResult,
            Model model, @RequestParam("noticeBoardImgFile") MultipartFile noticeBoardImgFileList) {
   		
   		if (bindingResult.hasErrors()) { // 상품 등록시 필수 값이 없다면 다시 상품 등록 페이지로 전환한다.
            return "/Board/Notice/noticeForm";
        }
        try {
            noticeboardservice.saveBoard(noticeBoardFormDto, noticeBoardImgFileList); // 공지사항 저장 로직을 호출. 공지사항정보와 공지사항이미지정보를 넘긴다.
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "/Board/Notice/noticeForm";
        }
        return "redirect:/ko/notice"; // 정상적으로 등록되었다면 공지사항페이지로 이동한다.
    }
   	
   	//공지사항 삭제 
   	@DeleteMapping(value="/notice/delete/{id}")
	public String noticedelete(@PathVariable Long id, @RequestParam("noticeImgIds") Long imgId){
   	
		noticeboardservice.deleteBoard(id, imgId); //boardService에 있는 deleteBoarddeleteBoard(id, imgId)를 호출
   		return "redirect:/ko/notice";
	}
   	
   	//공지사항 수정 페이지
	@GetMapping(value="/notice/modify/{id}")
	public String noticeModify(@PathVariable("id") Long id,Model model){
   		
		try {
			NoticeBoardFormDto noticeBoardFormDto = noticeboardservice.getNoticeBoardDtl(id);
			model.addAttribute("noticeBoardFormDto", noticeBoardFormDto);
			
		}catch (EntityNotFoundException e){
			model.addAttribute("errorMessage", "존재하지 않는 공지사항입니다.");
			model.addAttribute("noticeBoardFormDto", new NoticeBoardFormDto());
			
			return "/Board/Notice/updateform";
		}

		
		
		return "/Board/Notice/updateform";
	}
	
	//공지사항 수정 적용
	@PutMapping(value="/notice/update/{id}")
	public String noticeUpdate(@Valid NoticeBoardFormDto noticeBoardFormDto, BindingResult bindingResult,
			@RequestParam("noticeBoardImgFile") MultipartFile noticeBoardImgFileList, Model model){
		
		if (bindingResult.hasErrors()) {
			return "/Board/Notice/updateform";
		}
		
		try {
			noticeboardservice.updateBoard(noticeBoardFormDto, noticeBoardImgFileList);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "공지사항 수정 중 에러가 발생하였습니다.");
			return "/Board/Notice/updateform";
		}
		
		return "redirect:/ko/notice";
	}
	
	// QnA 페이지
	@GetMapping(value="/qna")
	public String qna(){
		return "Board/QnA/qna";
	}
 
//    //QNA 상세 페이지
//    @GetMapping(value="/qna/detail")
//    public String qnadetail(){
//        return "/QnA/qnadetail";
//    }
//    
//    //QNA 작성 페이지
//    @GetMapping(value="/qna/form")
//    public String qnaform(){
//        return "/QnA/qnaform";
//    }

	// 자유게시판
	@GetMapping(value="/freeboard")
	public String freeboard(){
		return "Board/Freeboard/freeboard";
	}
	
}