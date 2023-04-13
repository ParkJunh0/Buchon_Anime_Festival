package com.biaf.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.biaf.dto.ReservationFormDto;

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
	
	private String select_day; // 선택날짜  
	
	private String movie_turn; //회차 
	
	@OneToOne(mappedBy="reservation")
	private Member members;
	
	@ManyToOne
	@JoinColumn(name="movie_id")
	private Movie movie;
	

	public static Reservation createNumber(ReservationFormDto reserDto, Movie movie,Member member) {
	    Reservation reser = new Reservation();
	    reser.setMembers(member);
	    System.out.println(member);
	    reser.setMovie(movie);
	    System.out.println(movie);
	    // reser.setPeople_num(reserDto.getPeople_num());
	    reser.setMovie_turn(reserDto.getMovie_turn());
	    reser.setSelect_day(reserDto.getSelect_day());
	    reser.setMovie_turn(reserDto.getMovie_turn());
	    reser.setSeat(reserDto.getSeat());
	    reser.setReser_num(reserDto.getSelect_day() + member.getMemberId() + movie.getId());
	    return reser;
	}
}
