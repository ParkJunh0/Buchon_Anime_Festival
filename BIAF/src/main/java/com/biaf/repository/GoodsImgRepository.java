package com.biaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biaf.entity.GoodsImg;

public interface GoodsImgRepository extends JpaRepository<GoodsImg, Long> {
    
    GoodsImg findByGoodsIdOrderByIdAsc(Long goodsId);

    @Query(value="select * from goods_img where goods_id = :goodsId", nativeQuery=true)
    GoodsImg goodsfind(Long goodsId);
    
    GoodsImg findByGoodsIdAndRepimgYn(Long goodsId, String repimgYn);
}