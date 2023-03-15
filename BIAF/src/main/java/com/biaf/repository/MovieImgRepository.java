package com.biaf.repository;

import com.biaf.entity.MovieImg;
import org.springframework.data.jpa.repository.JpaRepository; 

public interface MovieImgRepository extends JpaRepository<MovieImg, Long> { 
	
}
