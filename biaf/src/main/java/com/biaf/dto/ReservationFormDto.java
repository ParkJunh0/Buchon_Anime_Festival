package com.biaf.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import com.biaf.constant.ReserStatus;
import com.biaf.entity.Member;
import com.biaf.entity.Movie;
import com.biaf.entity.Reservation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationFormDto {

	private Long Id;

	private String seat;

	private Member member;

	private String people_num; // 인원수 

	private Long movieId;
	private String movieNm;

	private String cinema;
	private String reser_num;// 예매번호

	private String movie_turn; // 회차

	private String select_day; // 선택날짜
	private String imgUrl;
	
	private ReserStatus status;
	
	 private String memberEmail; 
	
	private LocalDateTime updateTime; //등록일


	public static List<ReservationFormDto> createReservationFormDto(List<Reservation> reser) {
		List<ReservationFormDto> reserDtoList = new ArrayList<ReservationFormDto>();
		ReservationFormDto reserDto;
		for (Reservation res : reser) {
			reserDto = new ReservationFormDto();
			reserDto.setId(res.getId());
			reserDto.setSeat(res.getSeat());
			reserDto.setMember(res.getMember());
			reserDto.setReser_num(res.getReser_num());
			reserDto.setMovie_turn(res.getMovie_turn());
			reserDto.setSelect_day(res.getSelect_day());
			reserDto.setUpdateTime(res.getUpdateTime());
			reserDto.setMovieNm(res.getMovie().getMovieNm());
			reserDto.setCinema(res.getMovie().getCinema());
			reserDto.setPeople_num(res.getPeople_num());
			reserDto.setImgUrl(res.getMovie().getMovieImg().getImgUrl());
			reserDto.setStatus(res.getReserStatus());
			reserDto.setMemberEmail(res.getMemberEmail());
			reserDtoList.add(reserDto);
		}

		return reserDtoList;
	}
	private static ModelMapper modelMapper = new ModelMapper();

	public Reservation createNumber() {
		return modelMapper.map(this, Reservation.class);
	}

	public static ReservationFormDto of(Reservation reservation) {
		return modelMapper.map(reservation, ReservationFormDto.class);
	}
}