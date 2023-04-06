package com.biaf.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.biaf.entity.NoticeBoard;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NoticeBoardFormDto {
	
	 private Long id;

	    @NotBlank(message = "제목은 필수 입력 값입니다.")
	    private String notice_title;

	    @NotNull(message = "내용은 필수 입력 값입니다.")
	    private String notice_content;
	    
	   

	  

	   
	   
	

	    private NoticeBoardImgDto noticeBoardImgDtoList = new NoticeBoardImgDto(); // 공지사항 저장 후 수정할 때 공지사항 이미지 정보를 저장하는 리스트

	    private Long noticeImgIds;

	    private static ModelMapper modelMapper = new ModelMapper();

	    public NoticeBoard createNotice() {
	        return modelMapper.map(this, NoticeBoard.class);
	    }

	    public static NoticeBoardFormDto of(NoticeBoard noticeBoard) {
	        return modelMapper.map(noticeBoard, NoticeBoardFormDto.class);
	    }
}
