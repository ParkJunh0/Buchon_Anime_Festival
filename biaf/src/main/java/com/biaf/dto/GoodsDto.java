package com.biaf.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GoodsDto {
    
    private Long id;

    private String goodsNm;

    private Integer price;

    private String goodsDetail;

    private String sellStatCd;

    private LocalDateTime regDateTime;

    private LocalDateTime updaTime;

}
