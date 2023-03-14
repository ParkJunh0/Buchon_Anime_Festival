package com.biaf.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.biaf.constant.GoodsSellStatus;

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
    @Column(name = "goods_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;	//상품 코드  
	private String goodsNm; //상품명  
	private int price; //가격
	private int stockNumber; //재고수량  
	private String goodsDetail; //상품 상세 설명
	private GoodsSellStatus goodsSellStatus; //상품 판매 상태
	private LocalDateTime regTime; // 등록시간
	private LocalDateTime updateTime; // 수정시간	
}
	