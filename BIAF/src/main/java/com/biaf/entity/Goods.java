package com.biaf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.biaf.constant.GoodsSellStatus;
import com.biaf.dto.GoodsFormDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Table(name="goods")
@ToString
@Entity
public class Goods extends baseEntity {

	@Id
    @Column(name = "goods_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goods_seq")
    @SequenceGenerator(name = "goods_seq", sequenceName = "goods_seq", allocationSize = 1)
    private Long id;	//상품 코드  
	private String goodsNm; //상품명  
	private int price; //가격
	private int stockNumber; //재고수량  
	private String goodsDetail; //상품 상세 설명	

	
	@Enumerated(EnumType.STRING)
	private GoodsSellStatus goodsSellStatus; //상품 판매 상태

	public void updateGoods(GoodsFormDto goodsFormDto){
		this.goodsNm = goodsFormDto.getGoodsNm();
		this.price = goodsFormDto.getPrice();
		this.stockNumber = goodsFormDto.getStockNumber();
		this.goodsDetail = goodsFormDto.getGoodsDetail();
		this.goodsSellStatus = goodsFormDto.getGoodsSellStatus();
	}
}