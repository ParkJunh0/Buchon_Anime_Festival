package com.biaf.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
	  Member findMember =memberRepository.findByEmail(member.getEmail()); 
	  if(findMember !=null) {
		  throw new IllegalStateException("이미 가입된 회원입니다"); 
		  }
	  }
	 

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		Member member = memberRepository.findByEmail(email);
		
		if(member == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return User.builder()
				.username(member.getEmail())
				.password(member.getMemberPassword())
				.roles(member.getRole().toString())
				.build();
				
	}
	
	
	
	

	   public Page<Member> memList(Pageable pageable){ //멤버조회,페이징
		      return memberRepository.findAll(pageable);
		   }

	  
	
	   public void memDelete(Long memberId) { //멤버삭제
		   memberRepository.deleteById(memberId);
		   
	   }
	   
	   
	   
	   public MemberFormDto findByEmail(String email) {
		   Member member = memberRepository.findByEmail(email);
		   if(member !=null) 
			   return MemberFormDto.createMemberFormDto(member);
		   return null;
		   
	   }


	public String findEmail(String memberName, String memberTel) {

		List<Member> member = memberRepository.findByMemberNameAndMemberTel(memberName ,memberTel);

		String membere = member.get(0).getEmail();
		// entity dto 변환
//		List<MemberResponseDto> membere = new ArrayList<MemberResponseDto>();
//		for(Member mem : member) {
//			MemberResponseDto memberea = new MemberResponseDto();
//			memberea = MemberResponseDto.of(mem);
//			
//			membere.add(memberea);
//		}
		return  membere;
			
	}

	public boolean memberEmailCheck(String email, String memberName) {

        Member member = memberRepository.findByEmail(email);
        if(member!=null && member.getMemberName().equals(memberName)) {
            return true;
        }
        else {
            return false;
        }
    }
	
	
	
  
	
	

}
