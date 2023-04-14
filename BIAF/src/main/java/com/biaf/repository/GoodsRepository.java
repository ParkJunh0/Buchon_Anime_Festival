package com.biaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biaf.entity.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Long>{
    
    Goods findByGoodsNm(String goodsNm);
}
