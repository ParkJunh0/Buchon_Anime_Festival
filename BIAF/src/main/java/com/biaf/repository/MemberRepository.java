package com.biaf.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biaf.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Member findByMemberEmail(String memberEmail);

	boolean existsByMemberEmail(String memberEmail); // 데이터가 존재하는지 확인 하기 위해 exists 쿼리를 사용

	void deleteByMemberEmail(String memberEmail);

}
