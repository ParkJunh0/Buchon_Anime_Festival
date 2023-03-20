package com.biaf.dto;

import java.util.ArrayList;
import java.util.List;

import com.biaf.entity.GoodsImg;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GoodsDto {
    
    private Long id;

    private String goodsNm;

    private Integer price;

    private String goodsDetail;

    private String sellStatCd;

    private String  imgUrl;
    

   



    public static List<GoodsDto> createGoodsDto(List<GoodsImg> gdList){
        List<GoodsDto> mvResDtoList = new ArrayList<GoodsDto>();
        GoodsDto goodsDto;
        for(GoodsImg gd : gdList) {
            goodsDto = new GoodsDto();
            goodsDto.id = gd.getId(); 
            goodsDto.goodsNm = gd.getGoods().getGoodsNm(); 
            goodsDto.imgUrl = gd.getImgUrl(); 
            goodsDto.price = gd.getGoods().getPrice(); 
           
          
           mvResDtoList.add(goodsDto);
  
           
        }
        return mvResDtoList;
  
     }
}
