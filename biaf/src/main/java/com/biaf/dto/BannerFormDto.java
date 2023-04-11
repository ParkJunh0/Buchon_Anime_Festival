package com.biaf.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;

import com.biaf.entity.Banner;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BannerFormDto {

    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private LocalDateTime bannerDate;

    private static ModelMapper modelMapper = new ModelMapper();
    
    public static BannerFormDto of(Banner banner){
        return modelMapper.map(banner, BannerFormDto.class);
    }
}
