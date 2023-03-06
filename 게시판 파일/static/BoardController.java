package com.biaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/ko")
public class BoardController {
    @GetMapping(value="/notice")
    public String notice(){
        return "/Notice/notice";
    }
    @GetMapping(value="/notice/detail")
	public String noticedetail(){
		return "/Notice/noticedetail";
	}
	@GetMapping(value="/notice/form")
	public String noticeform(){
		return "/Notice/noticeform";
	}

    @GetMapping(value="/qna")
    public String qna(){
        return "/QnA/qna";
    }
    @GetMapping(value="/freeboard")
    public String freeboard(){
        return "/FreeBoard/freeboard";
    }
	@GetMapping(value="/freeboard/detail")
	public String freeboarddetail(){
		return "/FreeBoard/freeboarddetail";
	}
	@GetMapping(value="/freeboard/form")
	public String freeboardform(){
		return "/FreeBoard/freeboardform";
	}
	
}
