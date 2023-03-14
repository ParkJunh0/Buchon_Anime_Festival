package com.biaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biaf.entity.GoodsImg;

public interface GoodsImgRepository extends JpaRepository<GoodsImg, Long> {
    
    List<GoodsImg> findByGoodsIdOrderByIdAsc(Long goodsId);
}
