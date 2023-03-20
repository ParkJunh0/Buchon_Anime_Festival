package com.biaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biaf.entity.GoodsImg;

public interface GoodsImgRepository extends JpaRepository<GoodsImg, Long> {
    
    GoodsImg findByGoodsIdOrderByIdAsc(Long goodsId);

    GoodsImg findByGoodsIdAndRepimgYn(Long goodsId, String repimgYn);
}
