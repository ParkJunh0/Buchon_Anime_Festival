package com.biaf.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.biaf.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

//	Page<Movie> getMoviePage(Pageable pageable);
}
