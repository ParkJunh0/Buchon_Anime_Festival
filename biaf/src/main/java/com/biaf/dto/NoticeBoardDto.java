// package com.biaf.dto;

// import org.modelmapper.ModelMapper;
// import org.springframework.web.multipart.MultipartFile;

// import com.biaf.entity.NoticeBoard;

// import lombok.Data;


// @Data

// public class NoticeBoardDto {

// 	private Long id;
// 	private String notice_title;
// 	private String notice_content;
	
// 	private MultipartFile boardFile; //save.html에서 컨트롤러로 넘어올때 파일 담는 용도
// 	private String originalFileName; //원본 파일 이름
// 	private String storedFileName; //서버 저장용 파일 이름
// 	private int fileAttached; // 파일첨부 여부(첨부1, 미첨부0)
	
// 	private static ModelMapper modelMapper = new ModelMapper();
	
// 	public NoticeBoard createNoticeBoard() {
// 		return modelMapper.map(this, NoticeBoard.class);
// 	}
	
// }
