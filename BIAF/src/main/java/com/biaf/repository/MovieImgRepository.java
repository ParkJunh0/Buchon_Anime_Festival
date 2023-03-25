package com.biaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biaf.entity.MovieImg; 

public interface MovieImgRepository extends JpaRepository<MovieImg, Long> { 
	
	MovieImg findByMovieIdOrderByIdAsc(Long id);	

}
