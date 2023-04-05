package com.biaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.biaf.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>, QuerydslPredicateExecutor<Movie>{
  
}
