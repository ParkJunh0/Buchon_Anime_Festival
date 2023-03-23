package com.biaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biaf.entity.Member;


public interface MemberRepository extends JpaRepository<Member, Long>{

   
   Member findByMemberEmail(String memberEmail);
   boolean existsByMemberEmail(String memberEmail);

   
	
	
	

	

}
