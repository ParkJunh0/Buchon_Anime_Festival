package com.biaf.dto;

import org.modelmapper.ModelMapper;

import com.biaf.entity.FreeBoardImg;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FreeBoardImgDto {
	
	private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn; //날짜
    
    private static ModelMapper modelMapper = new ModelMapper(); // 멤버 변수로 ModelMapper 객체 추가

    public static FreeBoardImgDto of(FreeBoardImg freeBoardImg) {
    	
    	if(freeBoardImg == null) {
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(freeBoardImg, FreeBoardImgDto.class);
    }

}
