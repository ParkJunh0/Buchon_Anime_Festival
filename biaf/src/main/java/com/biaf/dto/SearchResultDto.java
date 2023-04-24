package com.biaf.dto;

import java.util.List;

import com.biaf.entity.Words;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SearchResultDto {
    List<MovieResponseDto> movie;
    List<GoodsDto> goods;
    

    List<Words> rank;

    public void movieadd(List<MovieResponseDto> movie){
        this.movie=movie;
    }
    public void goodsadd(List<GoodsDto> goods){
        this.goods = goods;
    }

    public void rank(List<Words> words){
        this.rank = words;
    }
}
