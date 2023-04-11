package com.biaf.dto;

import java.util.ArrayList;
import java.util.List;

import com.biaf.constant.GoodsSellStatus;
import com.biaf.entity.GoodsImg;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GoodsDto {
    
    private Long id;

    private String goodsNm;

    private Integer price;

    private String goodsDetail;

    private GoodsSellStatus sellStat;

    private int stocknumber;

    private String  imgUrl;
    
    
    public static List<GoodsDto> createGoodsDto(List<GoodsImg> gdList){
        List<GoodsDto> mvResDtoList = new ArrayList<GoodsDto>();
        GoodsDto goodsDto;
        for(GoodsImg gd : gdList) {
            goodsDto = new GoodsDto();
            goodsDto.id = gd.getId(); 
            goodsDto.goodsNm = gd.getGoods().getGoodsNm(); 
            goodsDto.imgUrl = gd.getImgUrl(); 
            goodsDto.sellStat = gd.getGoods().getGoodsSellStatus();
            goodsDto.price = gd.getGoods().getPrice();
            goodsDto.goodsDetail = gd.getGoods().getGoodsDetail();
            goodsDto.stocknumber = gd.getGoods().getStockNumber();         
          
           mvResDtoList.add(goodsDto);
        }
        return mvResDtoList;
  
     }
}