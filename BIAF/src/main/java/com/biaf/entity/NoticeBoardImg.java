package com.biaf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="noticeboard_img")
@Getter @Setter
public class NoticeBoardImg extends BaseEntity {
	
	@Id
	@Column(name = "notice_img_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notice_board_seq")
	@SequenceGenerator(name = "notice_board_seq", sequenceName = "notice_board_seq", allocationSize = 1)
	private Long id;
	
	  private String imgName; // 이미지 파일명
	  private String oriImgName; // 원본 이미지 파일명
	  private String imgUrl; // 이미지 조회 경로
	  private String repimgYn; // 대표 이미지 여부
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "notice_id")
	    private NoticeBoard noticeBoard;

	    public void updateNoticeBoardImg(String oriImgName, String imgName, String imgUrl) {
	        this.oriImgName = oriImgName;
	        this.imgName = imgName;
	        this.imgUrl = imgUrl;
	    }
}
