package com.biaf.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.biaf.constant.GoodsSellStatus;
import com.biaf.entity.Goods;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsFormDto {

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String goodsNm;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotBlank(message = "상품 상세는 필수 입력 값입니다.")
    private String goodsDetail;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stockNumber;

    private GoodsSellStatus goodsSellStatus; // 상품 판매상태

    private GoodsImgDto goodsImgDtoList = new GoodsImgDto(); // 상품 저장 후 수정할 때 상품 이미지 정보를 저장하는 리스트

    private Long goodsImgIds;

    private static ModelMapper modelMapper = new ModelMapper();

    public Goods createGoods() {
        return modelMapper.map(this, Goods.class);
    }

    public static GoodsFormDto of(Goods goods) {
        return modelMapper.map(goods, GoodsFormDto.class);
    }

}
