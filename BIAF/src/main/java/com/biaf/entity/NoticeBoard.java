package com.biaf.entity;

 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.biaf.dto.NoticeBoardDto;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="NOTICE") 
@Data

public class NoticeBoard extends BaseTimeEntity {

	@Id
	@Column(name="notice_id")//, columnDefinition="numeric(19,0)"
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
//	private String notice_title;
	
	private String notice_title;
	
	private String notice_content;
	
	
	
	public  static NoticeBoard createnoiticeBoard(NoticeBoardDto noiticeBoardDto) {
		NoticeBoard noticeBoard = new NoticeBoard();
		noticeBoard.setNotice_title(noiticeBoardDto.getNotice_title());
		noticeBoard.setNotice_content(noiticeBoardDto.getNotice_content());
		noticeBoard.setId(noiticeBoardDto.getId());
        return noticeBoard;

  }


}
