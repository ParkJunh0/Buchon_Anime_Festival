package com.biaf.dto;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;

import com.biaf.entity.FreeBoard;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class FreeBoardDto {
	
	private Long id;
	private String freeboard_title;
	private String freeboard_content;

	
private LocalDateTime regTime;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public FreeBoard createFreeBoard() {
		return modelMapper.map(this, FreeBoard.class);
	}

	public static FreeBoardDto of(FreeBoard freeBoard) {
		return modelMapper.map(freeBoard, FreeBoardDto.class);
	}
	
}
