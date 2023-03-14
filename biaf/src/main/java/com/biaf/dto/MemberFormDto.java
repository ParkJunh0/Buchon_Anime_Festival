package com.biaf.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;


@Data
public class MemberFormDto {
	
	@NotBlank(message="이름은 필수 입력 값입니다.")
	private String memberName;  
	private Long memberId; 
	
	@NotEmpty(message="이메일은 필수 입력값입니다")
	@Email(message="이메일 형식으로 입력해주세요")
	private String memberEmail;
	
	@NotEmpty(message="비밀번호는 필수 입력 값입니다")
	@Length(min=6,max=16,message="비밀번호는 6자이상 16자 이하로 입력해주세요")
	private String memberPassword;
	
	@NotEmpty(message="주소값은 필수 입력 값입니다")
	private String memberAddress;
	@NotEmpty(message="전화번호는 필수 입력 값입니다")
	private String memberTel;
	@NotEmpty(message="우편번호는 필수 입력 값입니다")
	private String postcode;
	private String wRestAddress;
	private String memberLoc;
	 
	
	/*
	 * public static MemberFormDto toMemberDto(Member member) { MemberFormDto
	 * memberFormDto = new MemberFormDto(); memberFormDto.setId(member.getId());
	 * memberFormDto.setMemberEmail(member.getMemberEmail());
	 * memberFormDto.setMemberPassword(member.getMemberPassword());
	 * memberFormDto.setMemberAddress(member.getMemberAddress());
	 * memberFormDto.setMemberName(member.getMemberName()); return memberFormDto; }
	 */
}