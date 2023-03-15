package com.biaf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.biaf.constant.Role;
import com.biaf.dto.MemberFormDto;
import com.biaf.dto.MemberResponseDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member extends BaseEntity {

	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long memberId; // 아이디

	private String memberName; // 이름

	@Column(unique = true)
	private String memberEmail; // 이메일

	private String memberPassword; // 비밀번호

	private String memberAddress; // 주소
	/*
	 * private String memberLoc; // 성별
	 */
	private String postcode; // 우편번호
	private String wRestAddress; // 상세주소
	private String memberTel; // 전화번호

	@Enumerated(EnumType.STRING)
	private Role role;

	public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		Member member = new Member();
		System.out.println(memberFormDto.getMemberAddress());
		member.setMemberName(memberFormDto.getMemberName()); // 이름

		member.setMemberEmail(memberFormDto.getMemberEmail()); // 이메일
		member.setMemberAddress(memberFormDto.getMemberAddress()); // 주소
		member.setWRestAddress(memberFormDto.getWRestAddress()); // 나머지 주소
		member.setPostcode(memberFormDto.getPostcode());// 우편번호
		member.setMemberTel(memberFormDto.getMemberTel());
		// 전화번호
		/*
		 * member.setMemberLoc(memberFormDto.getMemberLoc()); //성별
		 */ String passwrod = passwordEncoder.encode(memberFormDto.getMemberPassword()); // 비밀번호
		member.setMemberPassword(passwrod);
		member.setRole(Role.USER);
		return member;

	}

	
//	  public static Member createMember(MemberResponseDto memberResDto, PasswordEncoder passwordEncoder) {  //관리자가 계정생성
//	  Member member = new Member();
//	  member.setMemberName(memberResDto.getMemberName()); // 이름
//	  member.setMemberEmail(memberResDto.getMemberEmail()); // 이메일
//	  
//	  member.setMemberAddress(memberResDto.getMemberAddress()); //주소
//	  member.setWRestAddress(memberResDto.getWRestAddress()); //나머지 주소
//	  member.setPostcode(memberResDto.getPostcode());//우편번호
//	  
//	  member.setMemberTel(memberResDto.getMemberTel()); //전화번호
//	  member.setRole(memberResDto.getRole()); return member; 
//	  }
	 

}
