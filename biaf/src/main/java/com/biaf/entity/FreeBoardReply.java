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

import com.biaf.dto.FreeBoardFormDto;
import com.biaf.dto.FreeBoardReplyDto;
import com.biaf.dto.FreeBoardReplyFormDto;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="freeboard_reply")
@Getter @Setter
public class FreeBoardReply extends BaseTimeEntity {
	
	@Id
	@Column(name = "freeboard_reply_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "free_board_seq")
	@SequenceGenerator(name = "free_board_seq", sequenceName = "free_board_seq", allocationSize = 1)
	private Long id;
	
	@Column(length = 30000)
	private String freeboard_reply_content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "freeboard_id")
	    private FreeBoard freeBoard;
	
	  public void createFreeBoardReply(FreeBoardReplyFormDto freeBoardReplyFormDto, FreeBoard freeBoard) {
		  	this.freeBoard = freeBoard;
	        this.freeboard_reply_content = freeBoardReplyFormDto.getFreeboard_reply_content();
	    }

}
