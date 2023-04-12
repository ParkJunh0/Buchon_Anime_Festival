package com.biaf.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.biaf.dto.GoodsDto;
import com.biaf.dto.GoodsFormDto;
import com.biaf.entity.Goods;
import com.biaf.service.GoodsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/ko")
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
        
        for(GoodsDto gooods : goodsService.findAll()){
            if(goodsFormDto.getGoodsNm().equals(gooods.getGoodsNm())){
                model.addAttribute("errorMessage", "이미 있는 상품 이름 입니다.");
                return "admin/goodsForm";
            }
        }
        
        try {
            goodsService.saveGoods(goodsFormDto, goodsImgFileList); // 상품 저장 로직을 호출. 상품정보와 상품이미지정보를 넘긴다.
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "admin/goodsForm";
        }
        return "redirect:/ko/admin/goods"; // 정상적으로 등록되었다면 메인페이지로 이동한다.
    }

    @GetMapping(value = "/admin/goods/{goodsId}") // url 경로 변수는 { } 표현한다.
    public String goodsDtl(@PathVariable("goodsId") Long goodsId, Model model) {
        try {
            GoodsFormDto goodsFormDto = goodsService.getGoodsDtl(goodsId); // 조회한 상품 데이터를 모델에 담아 뷰로 전달한다.
            model.addAttribute("goodsFormDto", goodsFormDto);

        } catch (EntityNotFoundException e) { // 상품 엔티티가 존재하지 않을 경우 에러 메시지를 담아 상품 등록 페이지로 이동한다.
            model.addAttribute("errorMessage", "존재하지 않는 상품 입니다.");
            model.addAttribute("goodsFormDto", new GoodsFormDto());
            return "admin/goodsForm";
        }
        return "admin/goodsForm";
    }

    @PostMapping(value = "/admin/goods/{goodsId}")
    public String goodsUpdate(@Valid GoodsFormDto goodsFormDto, BindingResult bindingResult,
            @RequestParam("goodsImgFile") MultipartFile goodsImgFileList, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/goodsForm";
        }
        if (goodsImgFileList.isEmpty() && goodsFormDto.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "admin/goodsForm";
        }
        for(GoodsDto gooods : goodsService.findAll()){
            if(goodsFormDto.getGoodsNm().equals(gooods.getGoodsNm())){
                model.addAttribute("errorMessage", "이미 있는 상품 이름 입니다.");
                return "admin/goodsForm";
            }
        }
        try {
            goodsService.updateGoods(goodsFormDto, goodsImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
            return "admin/goodsForm";
        }
        return "redirect:/ko/admin/goods";
    }

    @PostMapping("admin/goods/delete/{goodsId}")
    public String Goodsdelete(@PathVariable Long goodsId, @RequestParam("goodsImgIds") Long imgId) {
        goodsService.goodsDelete(goodsId, imgId);

        return "redirect:/ko/admin/goods";
    }


    @GetMapping(value = "admin/goods") // 굿즈등록관리(굿즈리스트)
    public String goodMng(Model model, @PageableDefault(page=0, size=5, direction=Sort.Direction.DESC) Pageable pageable) {

        Page<Goods> gdlist = goodsService.gdList(pageable);
        model.addAttribute("goodsDto", gdlist);
                
        int nowPage = gdlist.getPageable().getPageNumber() + 1;    //페이징   
        int startPage =  Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage+9, gdlist.getTotalPages());
    
        model.addAttribute("gdlist", gdlist);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        
        return "admin/goodsMng";
    }
}
