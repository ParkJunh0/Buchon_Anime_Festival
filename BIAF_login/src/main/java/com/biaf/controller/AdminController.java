package com.biaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/ko")
public class AdminController {
	@GetMapping(value="/adminmenu")
	public String adminmenu() {
		return "/admin/adminmenu";
	}
}
