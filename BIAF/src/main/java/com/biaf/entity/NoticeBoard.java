package com.biaf.entity;

 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.biaf.dto.NoticeBoardDto;
import com.biaf.dto.NoticeBoardFormDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="NOTICE") 
@Data
@Getter @Setter
public class NoticeBoard extends BaseTimeEntity {

	@Id
	@Column(name="notice_id")//, columnDefinition="numeric(19,0)"
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notice_seq")
	@SequenceGenerator(name = "notice_seq", sequenceName = "notice_seq", allocationSize = 1)
	private Long id;
//	private String notice_title;
	
	
	private String notice_title;
	
	@Column(length = 30000)
	private String notice_content;
	
	public  static NoticeBoard createnoiticeBoard(NoticeBoardDto noiticeBoardDto) {
		NoticeBoard noticeBoard = new NoticeBoard();
		noticeBoard.setNotice_title(noiticeBoardDto.getNotice_title());
		noticeBoard.setNotice_content(noiticeBoardDto.getNotice_content());
		noticeBoard.setId(noiticeBoardDto.getId());
        return noticeBoard;

  }

  public void updateNoticeBoard(NoticeBoardFormDto noticeBoardFormDto) {
	this.id = noticeBoardFormDto.getId();
	this.notice_title = noticeBoardFormDto.getNotice_title();
	this.notice_content = noticeBoardFormDto.getNotice_content();
	
}
}
