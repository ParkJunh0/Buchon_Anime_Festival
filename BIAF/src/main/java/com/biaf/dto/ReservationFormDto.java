package com.biaf.dto;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.biaf.entity.Member;
import com.biaf.entity.Reservation;

import lombok.Data;

@Data
public class ReservationFormDto {
	
	private Long Id;
	
	private String seat;
	
	private Member member;
	
//	private int people_num; // 인원수 
	
	private Long movieId;
	
	private String reser_num;// 예매번호 
	
	private String movie_turn; //회차 
	
	private String select_day; //선택날짜 
	
	public static ReservationFormDto createReservationFormDto(Reservation reser) {
		ReservationFormDto reserFormDto = new ReservationFormDto();
		reserFormDto.Id=reser.getId();
		reserFormDto.seat=reser.getSeat();
		reserFormDto.member=reser.getMembers();
		reserFormDto.reser_num=reser.getReser_num();
		reserFormDto.movie_turn=reser.getMovie_turn();
		reserFormDto.select_day=reser.getSelect_day();
		reserFormDto.movieId=reser.getMovie().getId();
		
		return reserFormDto;
	}
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Reservation createNumber() {
		return modelMapper.map(this, Reservation.class);
	}

}
