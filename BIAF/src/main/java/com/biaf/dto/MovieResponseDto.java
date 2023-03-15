package com.biaf.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MovieResponseDto {

	private Long id;
	private String movieNm;
	private String imgUrl;
	private String startDay; // 상영 시작일	
	private String endDay; // 상영 종료일
	
	@QueryProjection // 생성자에 @QueryProjection 선언하여 Querydsl로 결과 조회 시 MainItemDto 객체로 바로 받아오도록 활용한다.
	public MovieResponseDto(Long id, String movieNm, String imgUrl, String startDay, String endDay){
		this.id = id;
		this.movieNm = movieNm;
		this.imgUrl = imgUrl;
		this.startDay = startDay;
		this.endDay = endDay;

	}
}
