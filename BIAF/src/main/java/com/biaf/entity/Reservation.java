package com.biaf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.biaf.constant.ReserStatus;
import com.biaf.dto.ReservationFormDto;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="reservation")
@Getter
@Setter
public class Reservation extends BaseTimeEntity{

	@Id
	@Column(name="reservation_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String seat; //좌석
	
	private String reser_num;// 예매번호 
	
	private String people_num; // 인원수 
	
	private String select_day; // 선택날짜  
	
	private String movie_turn; //회차 
	
	@OneToOne(mappedBy="reservation")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name="movie_id")
	private Movie movie;
	
	
	private String memberEmail;

	 @Enumerated(EnumType.STRING)
	    private ReserStatus reserStatus;
	 
	public static Reservation createNumber(ReservationFormDto reserDto, Movie movie,Member member) {
	    Reservation reser = new Reservation();
	    reser.setMember(member);
	    System.out.println(member);
	    reser.setMemberEmail(member.getMemberEmail());
	    reser.setMovie(movie);
	    System.out.println(movie);
	    // reser.setPeople_num(reserDto.getPeople_num());
	    reser.setMovie_turn(reserDto.getMovie_turn());
	    reser.setPeople_num(reserDto.getPeople_num());
	    reser.setSelect_day(reserDto.getSelect_day());
	    reser.setMovie_turn(reserDto.getMovie_turn());
	    reser.setSeat(reserDto.getSeat());
	    reser.setReserStatus(ReserStatus.OK);
	    reser.setReser_num(reserDto.getSelect_day() + member.getMemberId() + movie.getId());
	    return reser;
	}
	
	public void cancle() {
		
		this.setReserStatus(ReserStatus.CANCLE);
	};
}
