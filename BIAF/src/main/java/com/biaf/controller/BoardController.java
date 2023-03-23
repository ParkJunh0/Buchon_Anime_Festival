package com.biaf.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biaf.dto.NoticeBoardDto;
import com.biaf.entity.NoticeBoard;
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
        List list = boardService.boardList();
        
    	model.addAttribute("noticeList", boardService.boardList());
        
        
        return "Notice/notice";
    }
    
    //공지사항 상세 페이지
    @GetMapping(value="/notice/detail/{id}")
	public String noticedetail(@PathVariable("id") Long id, Model model, HttpServletRequest request){
    	Optional<NoticeBoard> detail = boardService.findBoard(id);
    	HttpSession session = request.getSession();
    	session.setAttribute("title", detail.get().getNotice_title());
    	session.setAttribute("content", detail.get().getNotice_content());
    	session.setAttribute("id", detail.get().getId());
    	model.addAttribute("title", detail.get().getNotice_title());
    	model.addAttribute("content", detail.get().getNotice_content());
    	model.addAttribute("id", detail.get().getId());
		return "Notice/noticedetail";
	}
    
    //공지사항 작성 페이지
   	@GetMapping(value="/notice/form")
	public String noticeform(Model model){
   		model.addAttribute("noticeBoardDto", new NoticeBoardDto());
		return "Notice/noticeform";
	}
   	//공지사항 작성 적용
   	@PostMapping(value="/notice/write")
    public String noticewrite(@Valid NoticeBoardDto noticeBoardDto) {
   	   NoticeBoard noticeBoard = NoticeBoard.createnoiticeBoard(noticeBoardDto);
       boardService.saveBoard(noticeBoard);
       return "redirect:/ko/notice";
    }
   	
   	//공지사항 삭제 
   	@DeleteMapping(value="/notice/delete/{id}")
	public String noticedelete(@PathVariable Long id){
   		boardService.deleteBoard(id);
   		return "redirect:/ko/notice";
	}
   	
   	//공지사항 수정 페이지
	@GetMapping(value="/notice/modify")
	public String noticeModify(Model model){
   		model.addAttribute("noticeBoardDto", new NoticeBoardDto());
		return "Notice/updateform";
	}
	
	//공지사항 수정 적용
	@PutMapping(value="/notice/update/{id}")
	public String noticeUpdate(@PathVariable("id") Long id, @RequestParam("title") String title, @RequestParam("content") String content, NoticeBoardDto noticeBoardDto, Model model){
		NoticeBoard noticeBoard = boardService.updateBoard(id);
		
		noticeBoardDto.setId(id);
		noticeBoardDto.setNotice_content(content);
		noticeBoardDto.setNotice_title(title);
		System.out.println("----------:" + title);
		System.out.println("----------:" + content);
		noticeBoard = NoticeBoard.createnoiticeBoard(noticeBoardDto);
		boardService.saveBoard(noticeBoard);
		
   		//model.addAttribute("noticeBoardDto", new NoticeBoardDto());
		return "redirect:/ko/notice";
	}
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
