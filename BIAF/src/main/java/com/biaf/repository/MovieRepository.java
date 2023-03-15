package com.biaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.biaf.dto.MovieFormDto;
import com.biaf.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>, QuerydslPredicateExecutor<Movie>{
	
//	List<Movie> findByMovieNm(String movieNm); // 영화이름조회


}
