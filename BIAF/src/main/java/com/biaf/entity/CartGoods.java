package com.biaf.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cart_goods")
public class CartGoods extends BaseEntity {
	// 장바구니에 담긴 상품 entity
	@Id
	@GeneratedValue
	@Column(name = "cart_goods_id")

	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id") // 하나의 장바구니 상품 엔티티에는 여러 상품을 담을 수 있기에 다대일 연관 관계

	private Cart cart;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "goods_id") // 장바구니 상품 엔티티에 담을 상품 정보를 알아야 하므로 상품 엔티티를 매핑해준다.
	
	private Goods goods;

	private int count; // 같은 상품을 장바구니에 몇 개 담을지 저장한다.

	public static CartGoods createCartGoods(Cart cart, Goods goods, int count) {
		CartGoods cartGoods = new CartGoods();
		cartGoods.setCart(cart);
		cartGoods.setGoods(goods);
		cartGoods.setCount(count);
		return cartGoods;
	}

	public void addCount(int count) { // 장바구니에 기존에 담겨 있는 상품인데, 해당 상품을 추가로 장바구니에 담을 때
									  // 기존 수량에 현재 담을 수량을 더해줄 때 사용할 메소드
		this.count += count;
	}

	public void updateCount(int count) { //상품의 수량을 변경
		this.count = count;
	}
}
