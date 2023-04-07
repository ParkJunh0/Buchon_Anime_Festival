package com.biaf.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.biaf.entity.Member;

import lombok.Data;


@Data
public class MemberFormDto {
	
	@NotBlank(message="이름은 필수 입력 값입니다.")
	private String memberName;  
	private Long memberId; 
	
	@NotEmpty(message="이메일은 필수 입력값입니다")
	@Email(message="이메일 형식으로 입력해주세요")
	private String email;
	
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
	
	
	 public static MemberFormDto createMemberFormDto(Member member) { 
		 
	MemberFormDto memberFormDto = new MemberFormDto();
	 memberFormDto.memberId= member.getMemberId();
	 memberFormDto.email = member.getEmail();
	 memberFormDto.memberPassword = member.getMemberPassword();
	 memberFormDto.memberAddress = member.getMemberAddress();
	 memberFormDto.memberTel = member.getMemberTel();
	 memberFormDto.postcode = member.getPostcode();
	 memberFormDto.wRestAddress = member.getWRestAddress();
	 return memberFormDto; }
	 
	 
	 
}
