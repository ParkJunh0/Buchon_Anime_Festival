package com.biaf.dto;

import org.modelmapper.ModelMapper;

import com.biaf.entity.MovieImg;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MovieImgDto {
	
	private Long id;
	private String imgName;
	private String oriImgName;
	private String imgUrl;
//	private String repImgYn;
	private static ModelMapper modelMapper = new ModelMapper(); // 멤버 변수로 ModelMapper 객체 추가
	
	public static MovieImgDto of(MovieImg movieImg) {
		return modelMapper.map(movieImg, MovieImgDto.class);
		// MovieImg 엔티티 객체를 파라미터로 받아서 MovieImg 객체의 자료형과 멤버변수의 이름이 같을 때 MovieImgDto로 값을 복사해서 반환.
		// static 메소드로 선언해 MovieImgDto 객체를 생성하지 않아도 호출할 수 있도록 함.
	}
	

}
