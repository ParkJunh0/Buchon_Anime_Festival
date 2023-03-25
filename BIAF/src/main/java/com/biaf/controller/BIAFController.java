package com.biaf.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biaf.dto.GoodsDto;
import com.biaf.service.GoodsService;
import com.google.common.collect.Lists;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/ko")
public class BIAFController {
	private final GoodsService goodsService;

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
    @GetMapping(value="/goods")
	public String goods(Model model){
		// 데이타베이스에서 굿즈 불러오기
		List<GoodsDto> goodsDto_all = goodsService.findAll();
		// 불러온 굿즈 2개씩 묶어서 정리
		List<List<GoodsDto>> goodsDto = Lists.partition(goodsDto_all, 2);
		// 모델에 등록
		model.addAttribute("goodsDto", goodsDto);
		return "BIAF/goods/Goods";
	}
}
