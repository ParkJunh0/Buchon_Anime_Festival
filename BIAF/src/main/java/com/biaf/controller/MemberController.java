package com.biaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/ko")
public class MemberController {
    @GetMapping(value="/login")
    public String login(){
        return "";
    }
    @GetMapping(value="/logout")
    public String logout(){
        return "";
    }
    @GetMapping(value="/signup")
    public String signup(){
        return "";
    }
}
