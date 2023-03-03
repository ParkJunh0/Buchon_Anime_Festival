package com.biaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/ko")
public class BoardController {
    @GetMapping(value="/notice")
    public String notice(){
        return "";
    }
    @GetMapping(value="/qna")
    public String qna(){
        return "";
    }
    @GetMapping(value="/freeboard")
    public String freeboard(){
        return "";
    }
}
