package com.biaf.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.biaf.entity.FreeBoard;
import com.biaf.entity.FreeBoardReply;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FreeBoardFormDto {
	
	 private Long id;

	    @NotBlank(message = "제목은 필수 입력 값입니다.")
	    private String freeboard_title;

	    @NotNull(message = "내용은 필수 입력 값입니다.")
	    private String freeboard_content;
	    
	   
	    
	    private FreeBoardImgDto freeBoardImgDtoList = new FreeBoardImgDto(); // 공지사항 저장 후 수정할 때 공지사항 이미지 정보를 저장하는 리스트
	    
	    private Long freeboardImgIds;

	   

	    private static ModelMapper modelMapper = new ModelMapper();

	    public FreeBoard createFreeBoard() {
	        return modelMapper.map(this, FreeBoard.class);
	    }

	    public static FreeBoardFormDto of(FreeBoard freeBoard) {
	        return modelMapper.map(freeBoard, FreeBoardFormDto.class);
	    }
	    
	    public FreeBoardReply createFreeBoardReply() {
	    	return modelMapper.map(this, FreeBoardReply.class);
	    	
	    }
	    
	    
	    

}
