package com.biaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biaf.entity.GoodsImg;

public interface GoodsImgRepository extends JpaRepository<GoodsImg, Long> {
    
    GoodsImg findByGoodsIdOrderByIdAsc(Long goodsId);

    @Query(value="select * from goods_img where goods_id = :goodsId", nativeQuery=true)
    GoodsImg goodsfind(Long goodsId);
    
    List<GoodsImg> findAllByOrderByGoods_GoodsSellStatusAsc();

    GoodsImg findByGoodsIdAndRepimgYn(Long goodsId, String repimgYn);
}