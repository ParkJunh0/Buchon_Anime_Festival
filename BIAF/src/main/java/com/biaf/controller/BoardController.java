package com.biaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biaf.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/ko")
public class BoardController {
	private final BoardService boardService;

	//공지사항 페이지
    @GetMapping(value="/notice")
    public String notice(Model model){
        model.addAttribute("noticeList", boardService.boardlist());
        return "/Notice/notice";
    }
    
//    //공지사항 상세 페이지
//    @GetMapping(value="/notice/detail")
//	public String noticedetail(){
//		return "/Notice/noticedetail";
//	}
//    
//    //공지사항 작성 페이지
//	@GetMapping(value="/notice/form")
//	public String noticeform(){
//		return "/Notice/noticeform";
//	}
//	
//	//QNA 페이지
//    @GetMapping(value="/qna")
//    public String qna(){
//        return "/QnA/qna";
//        
//    }
//    
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
//    
//    //공지사항 페이지
//    @GetMapping(value="/freeboard")
//    public String freeboard(){
//        return "/FreeBoard/freeboard";
//    }
//    
// 	//공지사항 상세 페이지
//	@GetMapping(value="/freeboard/detail")
//	public String freeboarddetail(){
//		return "/FreeBoard/freeboarddetail";
//	}
//	
//	//공지사항 작성 페이지
//	@GetMapping(value="/freeboard/form")
//	public String freeboardform(){
//		return "/FreeBoard/freeboardform";
//	}
	
}
