package com.biaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biaf.service.GoodsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value="/ko")
@RequiredArgsConstructor
public class BIAFController {
	private final GoodsService goodsservice;
    @GetMapping(value="/info")
    public String info(){
        return "BIAF/info/info";
    }
    @GetMapping(value="/logo_emot")
	public String logo_emot() {
		return "BIAF/logo_emot/logo_emot";
	}
    
    @GetMapping(value="/logo_emot2")
 	public String logo_emot2() {
 		return "BIAF/logo_emot/logo_emot2";
 	}
    

	@GetMapping(value = "/goods")
    public String goodsForm(Model model) {
        model.addAttribute("goodsDto", goodsservice.findAll());
        return "BIAF/goods/Goods";
    }
}
