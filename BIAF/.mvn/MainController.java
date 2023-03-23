package com.biaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
public class MainController {
	@GetMapping(value="/")
	public String firstpage() {
		return "FirstPage";
	}
	@GetMapping(value="/main")
	public String main() {
		return "MainPage";
	}
}
