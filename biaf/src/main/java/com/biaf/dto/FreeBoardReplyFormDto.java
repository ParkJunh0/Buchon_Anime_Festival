package com.biaf.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FreeBoardReplyFormDto {

	private Long id;
	
	
	private String freeboard_reply_content;
	
	private LocalDateTime regTime;
}
