package com.biaf.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="reservation")
@Getter
@Setter
public class Reservation {

	@Id
	@Column(name="reservation_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String seat; //좌석
	
	private String reser_num;// 예매번호 
	
	private int people_num; // 인원수 
	
	@OneToMany(mappedBy="reservation")
	List<Member> members = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="movie_id")
	private Movie movie;
}
