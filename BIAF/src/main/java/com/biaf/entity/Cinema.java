package com.biaf.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="cinema")
@Getter
@Setter
public class Cinema {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String startDay; // 상영 시작일
	
	private String endDay; // 상영 종료일
	
	private String cinema; //상영관 이름 
	
	private String time;
	
	private int seat;

	
}
