package com.biaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biaf.entity.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Long> {
    
}
