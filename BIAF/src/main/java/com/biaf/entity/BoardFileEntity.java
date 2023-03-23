package com.biaf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board_file")
public class BoardFileEntity extends baseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String originalFileName;
	
	@Column
	private String storedFileName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "notice_id")
	private NoticeBoard noticeBoard;
	
	public static BoardFileEntity toBoardFile(NoticeBoard noticeBoard, String originalFileName, String storedFileName) {
		BoardFileEntity boardFile = new BoardFileEntity();
		boardFile.setOriginalFileName(originalFileName);
		boardFile.setStoredFileName(storedFileName);
		boardFile.setNoticeBoard(noticeBoard);
		return boardFile;
	}
}
