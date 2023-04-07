package com.biaf.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDto {

	private Long id;
	private String movieNm; // 이름
	private String cinema; //상영관
	private Integer price; //영화 가격
	private String movieTime; // 관람시간
	private String movieDetail; //상세설명
	private String startDay; // 상영 시작일
	private String endDay; // 상영 종료일
	private String grade; //관람등급
//	private String imgUrl; //이미지 경로

}

