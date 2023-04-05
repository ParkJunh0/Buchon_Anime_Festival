package com.biaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biaf.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	// JpaRepository<대상으로 지정할 엔티티, 해당 엔티티의 PK의 타입>.

	Cart findByMemberMemberId(Long memberId); // 멤버 아이디를 조회
	// find + [엔터티 이름] + By + 변수이름  - 엔터티 이름은 생략 가능
}

