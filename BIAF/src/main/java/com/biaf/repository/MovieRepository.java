package com.biaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biaf.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

    @Query("SELECT m FROM Movie m WHERE m.movieNm LIKE %:words%")
	List<Movie> findAllMovieNmSEWith(@Param("words") String words);
  
}
