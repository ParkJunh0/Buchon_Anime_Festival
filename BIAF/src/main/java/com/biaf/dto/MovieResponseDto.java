package com.biaf.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.biaf.constant.MovieStatus;
import com.biaf.entity.MovieImg;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MovieResponseDto {
	// 현재 상영작 페이지, 영화등록관리 페이지

	private Long id;
	private String movieNm;
	private String imgUrl;
	private String startDay; // 상영 시작일	
	private String endDay; // 상영 종료일
	private MovieStatus movieStatus; // 상태
	private LocalDateTime regTime; //등록일
	private LocalDateTime updateTime; //등록일
	private Integer price;
//	private String createdBy; // 등록자

	public static List<MovieResponseDto> createMovieDto(List<MovieImg> mvList){
		List<MovieResponseDto> mvResDtoList = new ArrayList<MovieResponseDto>();
		MovieResponseDto mvResDto;
		for(MovieImg mv : mvList) {
			mvResDto = new MovieResponseDto();
			mvResDto.id = mv.getId();
			mvResDto.movieNm = mv.getMovie().getMovieNm();
			mvResDto.imgUrl = mv.getImgUrl();
//			System.out.println(mvResDto.imgUrl);
			mvResDto.startDay = mv.getMovie().getStartDay();
			mvResDto.endDay = mv.getMovie().getEndDay();
			mvResDto.movieStatus = mv.getMovie().getMovieStatus();
			mvResDto.regTime = mv.getMovie().getRegTime();
			mvResDto.updateTime = mv.getMovie().getUpdateTime();
			mvResDto.price = mv.getMovie().getPrice();
			
			mvResDtoList.add(mvResDto);
			
			System.out.println(mvResDto.regTime);
			
		}
		return mvResDtoList;

	}

//	@QueryProjection // 
//	public MovieResponseDto(Long id, String movieNm,String imgUrl,String startDay,String endDay){
//	this.id = id;
//	this.movieNm = movieNm;
//	this.imgUrl = imgUrl;
//	this.startDay = startDay;
//	this.endDay = endDay;
//	}
}	

