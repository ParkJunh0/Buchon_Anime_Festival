package com.biaf.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biaf.entity.Member;


public interface MemberRepository extends JpaRepository<Member, Long>{

   
   Member findByMemberEmail(String memberEmail);

	boolean existsByMemberEmail(String memberEmail);
   void deleteByMemberEmail(String memberEmail);

}
