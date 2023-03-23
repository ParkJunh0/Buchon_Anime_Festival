package com.biaf.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.biaf.constant.MovieStatus;
import com.biaf.entity.Movie;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MovieFormDto {
	// 영화등록 폼

	private Long id;

	@NotBlank(message = "영화명은 필수 입력 값입니다.")
	private String movieNm;

	@NotNull(message = "가격은 필수 입력 값입니다.")
	private Integer price;

	@NotBlank(message = "영화 상세는 필수 입력 값입니다.")
	private String movieDetail;

	private String cinema; // 상영관

	private String movieTime; // 관람시간

	private String startDay; // 상영 시작일

	private String endDay; // 상영 종료일

	private String grade; // 관람등급

	private MovieStatus movieStatus; // 영화 상태

	private MovieImgDto movieImgDtoList = new MovieImgDto();; // 상품 저장 후 수정할 때 상품 이미지 정보를 저장하는 리스트

	private Long movieImgIds;
	// 영화 이미지 아이디를 저장하는 리스트. 영화 등록 시에는 아직 영화의 이미지를 저장하지 않았기 떄문에 아무 값도 들어가 있지 않고 수정시에
	// 이미지 아이디를 담아둘 용도로 사용한다.

	
	private static ModelMapper modelMapper = new ModelMapper();

	public Movie createMovie() {
		return modelMapper.map(this, Movie.class);
	}

	public static MovieFormDto of(Movie movie) {
		return modelMapper.map(movie, MovieFormDto.class);
		// modelMapper를 이용하여 엔티티 객체와 DTO객체 간의 데이터를 복사하여 복사한 객체를 반환해주는 메소드
		// of(entity) : 엔티티를 DTO로 변환하는 작업을 위해 만든 메소드
	}

}
