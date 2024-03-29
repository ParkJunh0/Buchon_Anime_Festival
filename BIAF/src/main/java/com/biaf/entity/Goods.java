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
import com.biaf.dto.OrderDto;
import com.biaf.exception.OutOfStockException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Table(name = "goods")
@ToString
@Entity
public class Goods extends BaseEntity {

	@Id
	@Column(name = "goods_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goods_seq")
	@SequenceGenerator(name = "goods_seq", sequenceName = "goods_seq", allocationSize = 1)
	private Long id; // 상품 코드

	@Column(nullable = false, length = 50)
	private String goodsNm; // 상품명

	@Column(name="price", nullable = false)
	private int price; // 가격

	@Column(nullable = false)
	private int stockNumber; // 재고수량

	@Column(nullable = false)
	private String goodsDetail; // 상품 상세 설명

	// private LocalDateTime regTime; // 등록시간

	// private LocalDateTime updateTime; // 수정시간

	@Enumerated(EnumType.STRING)
	private GoodsSellStatus goodsSellStatus; // 상품 판매 상태

	public void updateGoods(GoodsFormDto goodsFormDto) {
		this.goodsNm = goodsFormDto.getGoodsNm();
		this.price = goodsFormDto.getPrice();
		this.stockNumber = goodsFormDto.getStockNumber();
		this.goodsDetail = goodsFormDto.getGoodsDetail();
		this.goodsSellStatus = goodsFormDto.getGoodsSellStatus();
	}

	public void removeStock(int stockNumber) {
		int restStock = this.stockNumber - stockNumber; // 상품재고 수량에서 주문 후 남은 재고수량을 구한다.
		if (restStock < 0) {
			throw new OutOfStockException("상품의 재고가 부족 합니다. (현재 재고 수량: " + this.stockNumber + ")");
		} // 상품재고가 주문 수량보다 적을 때 재고 부족 예외를 발생시킨다.
		this.stockNumber = restStock; // 주문 후 남은 재고 수량을 상품의 현재 재고 값으로 할당한다.
	}

	public void addStock(int stockNumber) {
		this.stockNumber += stockNumber;
		if(this.stockNumber > 1 ){
			this.goodsSellStatus = GoodsSellStatus.SELL;
		}
	}
	public void down(OrderDto order){
        this.stockNumber -= order.getCount();
		if(this.stockNumber == 0){
			this.goodsSellStatus = GoodsSellStatus.SOLD_OUT;
		}
	}
}
