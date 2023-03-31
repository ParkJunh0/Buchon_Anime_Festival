package com.biaf.dto;

import org.modelmapper.ModelMapper;

import com.biaf.entity.NoticeBoard;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter @Setter
public class NoticeBoardDto {

	private Long id;
	private String notice_title;
	private String notice_content;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public NoticeBoard createNoticeBoard() {
		return modelMapper.map(this, NoticeBoard.class);
	}
	
}
