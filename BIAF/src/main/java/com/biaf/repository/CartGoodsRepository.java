package com.biaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biaf.dto.CartDetailDto;
import com.biaf.entity.CartGoods; 

public interface CartGoodsRepository extends JpaRepository<CartGoods, Long> { 
	
	CartGoods findByCartIdAndGoodsId(Long cartId, Long goodsId); 
	
	@Query("select new com.biaf.dto.CartDetailDto(cg.id, g.goodsNm, g.price, cg.count, im.imgUrl) " +
		"from CartGoods cg, GoodsImg im " +
		"join cg.goods g " +
		"where cg.cart.id = :cartId " +
		"and im.goods.id = cg.goods.id " +
		"order by cg.regTime desc" 
		) 
	List<CartDetailDto> findCartDetailDtoList(Long cartId); 
	
}
