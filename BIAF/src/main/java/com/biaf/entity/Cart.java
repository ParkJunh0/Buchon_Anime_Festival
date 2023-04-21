package com.biaf.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cart")
@Getter @Setter
@ToString
public class Cart extends BaseEntity{

	@Id // 테이블의 기본키에 사용할 속성을 지정
	@Column(name = "cart_id") // 테이블의 필드와 엔터티의 칼럼 매핑
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long id;

	@OneToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "member_id") 
	private Member member;

	public static Cart createCart(Member member) {
		Cart cart = new Cart();
		cart.setMember(member);
		return cart;
	}

}
