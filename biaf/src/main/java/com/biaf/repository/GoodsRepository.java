package com.biaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biaf.entity.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Long>{
    
    Goods findByGoodsNm(String goodsNm);

    @Query("SELECT g FROM Goods g WHERE g.goodsNm LIKE %:words%")
    List<Goods> findAllBysearch(@Param("words") String words);
}
