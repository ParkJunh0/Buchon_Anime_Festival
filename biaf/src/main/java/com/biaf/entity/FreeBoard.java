package com.biaf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.biaf.dto.FreeBoardDto;
import com.biaf.dto.FreeBoardFormDto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="FreeBoard") 
@Data
@Getter @Setter
public class FreeBoard extends BaseTimeEntity {
	

	@Id
	@Column(name="freeboard_id")//, columnDefinition="numeric(19,0)"
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "freeboard_seq")
	@SequenceGenerator(name = "freeboard_seq", sequenceName = "freeboard_seq", allocationSize = 1)
	private Long id;
//	private String notice_title;
	
	
	private String freeboard_title;
	
	@Column(length = 30000)
	private String freeboard_content;
	

	
	
	
	public  static FreeBoard createFreeBoard(FreeBoardDto freeBoardDto) {
		FreeBoard freeBoard = new FreeBoard();
		freeBoard.setFreeboard_title(freeBoardDto.getFreeboard_title());
		freeBoard.setFreeboard_content(freeBoardDto.getFreeboard_content());
		freeBoard.setId(freeBoardDto.getId());
		
        return freeBoard;

  }

  public void updateFreeBoard(FreeBoardFormDto freeBoardFormDto) {
	this.id = freeBoardFormDto.getId();
	this.freeboard_title = freeBoardFormDto.getFreeboard_title();
	this.freeboard_content = freeBoardFormDto.getFreeboard_content();
	;
	
}
}


