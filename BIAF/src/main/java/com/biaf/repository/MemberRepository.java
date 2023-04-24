package com.biaf.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biaf.entity.Member;


public interface MemberRepository extends JpaRepository<Member, Long>{

   
   Member findByMemberEmail(String memberEmail);

	boolean existsByMemberEmail(String memberEmail);
   void deleteByMemberEmail(String memberEmail);

   List<Member> findByMemberNameAndMemberTel(String memberName, String memberTel);

   @Transactional
	@Modifying
    @Query("update Member m set m.memberPassword = :memberPassword where m.memberId = :memberId")
    void updateMemberPassword(@Param("memberId") Long memberId, @Param("memberPassword") String memberPassword);

}
