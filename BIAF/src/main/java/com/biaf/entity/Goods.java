package com.biaf.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.biaf.constant.GoodsSellStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Goods {

	@Id
	private Long id;	//상품 코드  private String itemNm; //상품명  private int price; //가격
	private int stockNumber; //재고수량  private String itemDetail; //상품 상세 설명
	private GoodsSellStatus itemSellStatus; //상품 판매 상태
	private LocalDateTime regTime; // 등록시간
	private LocalDateTime updateTime; // 수정시간	
}
	