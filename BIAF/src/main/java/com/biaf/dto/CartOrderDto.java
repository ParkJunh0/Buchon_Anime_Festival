package com.biaf.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List; 

@Getter @Setter 
public class CartOrderDto {  // 장바구니 목록에서 주문	
	
	private Long cartGoodsId; 
	
	private List<CartOrderDto> cartOrderDtoList; 
	// 장바구니에서 여러 개의 상품을 주문하므로 CartOrderDto 클래스가 자기 자신을 List로 가지고 있도록 만든다. }
}