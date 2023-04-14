package com.biaf.dto;

import org.modelmapper.ModelMapper;

import com.biaf.entity.NoticeBoardImg;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class NoticeBoardImgDto {
	
	  private Long id;

	    private String imgName;

	    private String oriImgName;

	    private String imgUrl;

	    private String repImgYn; //날짜
	    
	    private static ModelMapper modelMapper = new ModelMapper(); // 멤버 변수로 ModelMapper 객체 추가

	    public static NoticeBoardImgDto of(NoticeBoardImg noticeBoardImg) {
	        return modelMapper.map(noticeBoardImg, NoticeBoardImgDto.class);
	    }

}
