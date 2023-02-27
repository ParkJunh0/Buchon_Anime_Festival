package com.biaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/ko")
public class GoodsController {
	@GetMapping(value="/goods")
	public String firstpage() {
		return "goods/Goods";
	}
}
