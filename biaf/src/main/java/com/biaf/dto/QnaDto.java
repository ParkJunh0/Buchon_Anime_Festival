package com.biaf.dto;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;


import com.biaf.entity.Qna;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class QnaDto {
	
	private Long id;
	private String qna_answer;
	private String qna_question;
	
	private LocalDateTime regTime;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Qna createQna() {
		return modelMapper.map(this, Qna.class);
	}

	public static QnaDto of(Qna qna) {
		return modelMapper.map(qna, QnaDto.class);
	}

}
