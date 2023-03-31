package com.biaf.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class CartDetailDto { 
	
	private Long cartGoodsId; //장바구니 상품 아이디

	private String goodsNm; //상품명
	
	private int price; //상품 금액
	
	private int count; //수량
	
	private String imgUrl; //상품 이미지 경로
	
	public CartDetailDto(Long cartGoodsId, String goodsNm, int price, int count, String imgUrl){
		// 장바구니 페이지에 전달할 데이터를 생성자의 파라미터로 만들어준다. 
		this.cartGoodsId = cartGoodsId; 
		this.goodsNm = goodsNm; 
		this.price = price; 
		this.count = count; 
		this.imgUrl = imgUrl; 
	}
}
