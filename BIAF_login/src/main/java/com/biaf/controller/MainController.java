package com.biaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
public class MainController {
	@GetMapping(value="/ko/example")
	public String example(){
		return "Example";
	}
	@GetMapping(value="/")
	public String firstpage() {
		return "/main/FirstPage";
	}
	@GetMapping(value="/ko")
	public String main() {
		return "/main/MainPage";
	}
	@GetMapping(value="/ko/search")
	public String search(){
		return "/main/Search";
	}
}
