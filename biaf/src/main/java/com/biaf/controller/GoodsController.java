package com.biaf.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.biaf.dto.GoodsFormDto;
import com.biaf.service.GoodsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GoodsController {

    private final GoodsService goodsService;

    @GetMapping(value = "/admin/goods/new")
    public String goodsForm(Model model) {
        model.addAttribute("goodsFormDto", new GoodsFormDto());
        return "admin/goodsForm";
    }

    @PostMapping(value = "/admin/goods/new")
    public String goodsNew(@Valid GoodsFormDto goodsFormDto, BindingResult bindingResult,
            Model model, @RequestParam("goodsImgFile") MultipartFile goodsImgFileList) {
        if (bindingResult.hasErrors()) { // 상품 등록시 필수 값이 없다면 다시 상품 등록 페이지로 전환한다.
            return "admin/goodsForm";
        }
        if (goodsImgFileList.isEmpty() && goodsFormDto.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "admin/goodsForm"; // 상품 등록시 첫 번째 이미지가 없다면 에러 메시지와 함께 상품등록 페이지로 전환한다.
        } // 상품 첫번째 이미지는 메인 페이지에서 보여줄 상품 이미지를 사용하기 위해 필수 값으로 지정한다.
        try {
            goodsService.saveGoods(goodsFormDto, goodsImgFileList); // 상품 저장 로직을 호출. 상품정보와 상품이미지정보를 넘긴다.
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "admin/goodsForm";
        }
        return "redirect:/ko"; // 정상적으로 등록되었다면 메인페이지로 이동한다.
    }

}
