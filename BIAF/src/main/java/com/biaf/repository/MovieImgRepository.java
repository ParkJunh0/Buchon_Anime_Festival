package com.biaf.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biaf.entity.MovieImg; 

public interface MovieImgRepository extends JpaRepository<MovieImg, Long> { 
	
//	List<MovieImg> findByMovieIdOrderByIdAsc(Long id);	

}
