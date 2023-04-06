package com.biaf.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.biaf.dto.MemberFormDto;
import com.biaf.dto.MemberResponseDto;
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
	
	public List<MemberResponseDto> findAll(){
		return MemberResponseDto.createMemberDto(memberRepository.findAll());
	}

	public boolean findById(String memberEmail) { //ID(이메일)체크
		return memberRepository.existsByMemberEmail(memberEmail);
	}

	public Page<Member> memList(Pageable pageable){ //멤버조회,페이징
		return memberRepository.findAll(pageable);
	 }

public void memDelete(Long memberId) { //관리자가 멤버삭제
	 memberRepository.deleteById(memberId);
	 
 }
 
 public MemberFormDto mypagefindByMemberEmail(String memberEmail) { //마이페이지 내정보 가져오기
	 Member mem = memberRepository.findByMemberEmail(memberEmail);
	 if (mem != null)
		 return MemberFormDto.createMemberFormDto(mem);
	 return null;
 }

 public Member findByEmail(String email) { // 마이페이지 수정할때 회원찾기
	return memberRepository.findByMemberEmail(email);
}

public void updateMember(Member member) { // 마이페이지 수정하고 저장
	memberRepository.save(member);
}

 public void deletemember(String memberEmail) { //회원탈퇴
	 memberRepository.deleteByMemberEmail(memberEmail);
 }
}
