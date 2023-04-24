package com.biaf.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;


import com.biaf.entity.FreeBoardReply;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FreeBoardReplyDto {

	private Long id;
	
	private String freeboard_reply_content;
	
	private LocalDateTime regTime;
	
	private LocalDateTime updateTime;
	
	 private static ModelMapper modelMapper = new ModelMapper();
	 
	  public static FreeBoardReplyDto of(FreeBoardReply freeBoardreply) {
	        return modelMapper.map(freeBoardreply, FreeBoardReplyDto.class);
	    }

	 
	   
}
