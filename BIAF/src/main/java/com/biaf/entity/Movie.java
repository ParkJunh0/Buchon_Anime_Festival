package com.biaf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.biaf.constant.MovieStatus;
import com.biaf.dto.MovieFormDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "movie")
@Getter @Setter
@ToString
public class Movie extends BaseEntity{
	
	@Id
	@Column(name="movie_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id; // 영화 코드
	
	@Column(nullable = false, length = 50)
	private String movieNm; //영화이름	 
	
	private String cinema; // 상영관
	  
	private String movietime; // 관람시간
	
	private String startDay; // 상영 시작일
	
	private String endDay; // 상영 종료일
	
	private String grade; //관람등급
	
	@Column(name="movie_price", nullable = false)
	private Integer price;
	

	@Lob // BLOB(이미지, 동영상),CLOB(문자) 타입 매핑
	@Column(nullable = false)
	private String movieDetail; // 영화 상세 설명
	
	@Enumerated(EnumType.STRING) // enum 타입 매핑 
	private MovieStatus movieStatus; // 영화 상영 상태
	
	   public static Movie createmovie(MovieFormDto movieFormDto) {
		      Movie movie = new Movie();
		      movie.setMovieNm(movieFormDto.getMovieNm());
		      movie.setCinema(movieFormDto.getCinema());
		      movie.setStartDay(movieFormDto.getStartDay());
		      movie.setEndDay(movieFormDto.getEndDay());
		      movie.setGrade(movieFormDto.getGrade());
		      movie.setPrice(movieFormDto.getPrice());
		      movie.setMovieDetail(movieFormDto.getMovieDetail());
		      movie.setMovieStatus(movieFormDto.getMovieStatus());
		      movie.setId(movieFormDto.getId());
		      
		      return movie;

		}
}
