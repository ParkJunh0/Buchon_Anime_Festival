package com.biaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/ko")
public class LoginController {
    @GetMapping(value="/login")
    public String login(){
        return "/login/Login";
    }
    @GetMapping(value="/logout")
    public String logout(){
        return "";
    }
    @GetMapping(value="/signup")
    public String signup(){
        return "/login/signup";
    }
    @GetMapping(value="/terms")
	public String terms() {
		return "/login/terms";
	}
    @GetMapping(value="/join")
	public String join() {
		return "/login/join";
	}
	
	@GetMapping(value="/idfind")
	public String idfind() {
		return "/login/idfind";
	}
	
	@GetMapping(value="/pwfind")
	public String pwfind() {
		return "/login/pwfind";
	}
	
	@GetMapping(value="/pwreset")
	public String pwreset() {
		return "/login/pwreset";
	}
    
    
    
}
