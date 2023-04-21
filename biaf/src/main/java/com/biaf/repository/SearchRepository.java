package com.biaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biaf.entity.Words;

public interface SearchRepository extends JpaRepository<Words, Long>{

    List<Words> findAllByOrderByCountsDesc();
    
    
}
