package com.biaf.dto;

import org.modelmapper.ModelMapper;

import com.biaf.entity.GoodsImg;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GoodsImgDto {
    private Long id;
    private String imgName;
    private String oriImgName;
    private String imgUrl;
    private String repImgYn;
    private static ModelMapper modelMapper = new ModelMapper(); // 멤버 변수로 ModelMapper 객체 추가

    public static GoodsImgDto of(GoodsImg goodsImg) {
        return modelMapper.map(goodsImg, GoodsImgDto.class);
    }

}
