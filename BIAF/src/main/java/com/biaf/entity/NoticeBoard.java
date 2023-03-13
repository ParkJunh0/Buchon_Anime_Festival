package com.biaf.entity;

 import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="notice") 
@Data
@ToString
public class NoticeBoard {
	@Id
//	@Column(name="notice_id")
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	private Long id;
//	
//	private String title;
	
//	@Id
@Column(name="notice_id", columnDefinition="numeric(19,0)")
//	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private Long id;
	private String title;
	
	
	
	
	

}
