package com.biaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.biaf.entity.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Long>, QuerydslPredicateExecutor<Goods>{
    
    List<Goods> findByGoodsNm(String goodsNm);
}
