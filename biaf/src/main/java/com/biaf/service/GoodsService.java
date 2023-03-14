package com.biaf.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.biaf.dto.GoodsFormDto;
import com.biaf.entity.Goods;
import com.biaf.entity.GoodsImg;
import com.biaf.repository.GoodsImgRepository;
import com.biaf.repository.GoodsRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsRepository goodsRepository;
    private final GoodsImgService goodsImgService;
    private final GoodsImgRepository goodsImgRepository;

    public Long saveGoods(GoodsFormDto goodsFormDto, MultipartFile goodsImgFileList) throws Exception {
        // 상품 등록
        Goods goods = goodsFormDto.createGoods(); // 상품 등록 폼으로부터 입력 받은 데이터를 이용하여 goods 객체를 생성한다.
        goodsRepository.save(goods); // 상품 데이터를 저장한다.
        // 이미지 등록
        
            GoodsImg goodsImg = new GoodsImg();
            goodsImg.setGoods(goods);
            goodsImgService.saveGoodsImg(goodsImg, goodsImgFileList); // 상품 이미지 정보를 저장한다.
        
        return goods.getId();
    }

}
