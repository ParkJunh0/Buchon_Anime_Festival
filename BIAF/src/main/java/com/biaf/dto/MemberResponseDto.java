package com.biaf.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.biaf.constant.Role;
import com.biaf.entity.Member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberResponseDto {

	private Long memberId;
	private String memberName;  
	private String memberEmail;
	private String memberTel;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public static List<MemberResponseDto> createMemberDto(List<Member> memList){
		List<MemberResponseDto> memResDtoList = new ArrayList<MemberResponseDto>();
		MemberResponseDto memResDto;
		for(Member mem : memList) {
			memResDto = new MemberResponseDto();
			memResDto.memberId = mem.getMemberId();
			memResDto.memberName = mem.getMemberName();
			memResDto.memberEmail = mem.getMemberEmail();
			memResDto.memberTel = mem.getMemberTel();
			memResDto.role = mem.getRole();
			memResDtoList.add(memResDto);
		}
		return memResDtoList;
	}
	
}
