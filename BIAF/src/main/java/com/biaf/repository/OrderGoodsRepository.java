package com.biaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biaf.entity.OrderGoods;

public interface OrderGoodsRepository extends JpaRepository<OrderGoods, Long> {
    
}
