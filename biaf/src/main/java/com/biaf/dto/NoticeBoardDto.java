package com.biaf.dto;

import java.time.LocalDateTime;

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
	
	private LocalDateTime regTime;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public NoticeBoard createNoticeBoard() {
		return modelMapper.map(this, NoticeBoard.class);
	}

	public static NoticeBoardDto of(NoticeBoard notice) {
		return modelMapper.map(notice, NoticeBoardDto.class);
	}
}
