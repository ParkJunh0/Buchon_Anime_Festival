package com.biaf.service;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.biaf.entity.Member;
import com.biaf.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

	private final MemberRepository memberRepository;
	
	public Member saveMember(Member member) {
		validateDuplicateMember(member);
		return memberRepository.save(member);
	}

	
	  private void validateDuplicateMember(Member member) { // TODO Auto-generated
	  Member findMember =memberRepository.findByMemberEmail(member.getMemberEmail()); 
	  if(findMember !=null) {
		  throw new IllegalStateException("이미 가입된 회원입니다"); 
		  }
	  }
	 

	@Override
	public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException{
		Member member = memberRepository.findByMemberEmail(memberEmail);
		
		if(member == null) {
			throw new UsernameNotFoundException(memberEmail);
		}
		
		return User.builder()
				.username(member.getMemberEmail())
				.password(member.getMemberPassword())
				.roles(member.getRole().toString())
				.build();
				
	}
}
