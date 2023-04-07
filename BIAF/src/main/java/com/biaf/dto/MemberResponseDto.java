package com.biaf.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.modelmapper.ModelMapper;

import com.biaf.constant.Role;
import com.biaf.entity.Member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberResponseDto {

	private Long Id;
	private String memberName;  
	private String email;
	private String memberTel;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	private static ModelMapper modelMapper = new ModelMapper();
	public static List<MemberResponseDto> createMemberDto(List<Member> memList){
		List<MemberResponseDto> memResDtoList = new ArrayList<MemberResponseDto>();
		MemberResponseDto memResDto;
		for(Member mem : memList) {
			memResDto = new MemberResponseDto();
			memResDto.Id = mem.getMemberId();
			memResDto.memberName = mem.getMemberName();
			memResDto.email = mem.getEmail();
			memResDto.memberTel = mem.getMemberTel();
			memResDto.role = mem.getRole();
			memResDtoList.add(memResDto);
		}
		return memResDtoList;
	}
	public static MemberResponseDto of(Member memlist){
		return modelMapper.map(memlist, MemberResponseDto.class);
	}
	
}
