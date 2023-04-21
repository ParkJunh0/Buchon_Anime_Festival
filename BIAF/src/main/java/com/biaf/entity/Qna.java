package com.biaf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.biaf.dto.QnaDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="QNA") 
@Data
@Getter @Setter
public class Qna extends BaseTimeEntity {
	
	@Id
	@Column(name="qna_id")//, columnDefinition="numeric(19,0)"
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "qna_seq")
	@SequenceGenerator(name = "qna_seq", sequenceName = "qna_seq", allocationSize = 1)
	private Long id;

	@Column(length = 3000)
	private String qna_answer;
	
	@Column(length = 3000)
	private String qna_question;
	
	
	
	public static Qna createQna(QnaDto qnaDto) {
		Qna qna = new Qna();
		qna.setQna_answer(qnaDto.getQna_answer());
		qna.setQna_question(qnaDto.getQna_question());
		qna.setId(qnaDto.getId());
		
		return qna;
	
		
      

  }

  public void updateQna(QnaDto qnaDto) {
	this.id = qnaDto.getId();
	this.qna_answer = qnaDto.getQna_answer();
	this.qna_question = qnaDto.getQna_question();
	
	
}

}
