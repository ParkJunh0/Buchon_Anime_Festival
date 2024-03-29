package com.biaf.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.biaf.constant.Role;
import com.biaf.dto.MemberFormDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="member") 
@Getter
@Setter
@ToString
public class Member extends BaseEntity {

	@Id
	@Column(name="member_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long memberId; //아이디
	
	private String memberName; //이름
	
	@Column(unique=true)
	private String memberEmail; //이메일
	  
	private String memberPassword;  //비밀번호
	
	private String memberAddress; // 주소

	private String postcode; //우편번호
	private String wRestAddress; //상세주소
	private String memberTel; // 전화번호
	
	@Enumerated(EnumType.STRING)
	private Role role;

	@ManyToOne
	@JoinColumn(name="reservation_id")
	private Reservation reservation;
	
	public static Member createMem(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) { // 회원수정하기
		Member member = new Member();
		member.setMemberId(memberFormDto.getMemberId());
		member.setMemberEmail(memberFormDto.getMemberEmail()); // 이메일
		member.setMemberName(memberFormDto.getMemberName()); // 이름
		member.setRole(Role.USER);
		String password = passwordEncoder.encode(memberFormDto.getMemberPassword());
		member.setMemberPassword(password); // 비밀번호
		member.setMemberTel(memberFormDto.getMemberTel()); // 전화번호
		member.setPostcode(memberFormDto.getPostcode()); // 우편번호
		member.setMemberAddress(memberFormDto.getMemberAddress()); // 주소
		member.setWRestAddress(memberFormDto.getWRestAddress()); // 상세주소
		return member;
	}
	
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
}
