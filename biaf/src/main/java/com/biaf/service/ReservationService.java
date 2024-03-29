package com.biaf.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biaf.dto.MovieResponseDto;
import com.biaf.dto.ReservationFormDto;
import com.biaf.entity.Member;
import com.biaf.entity.Movie;
import com.biaf.entity.Reservation;
import com.biaf.repository.MemberRepository;
import com.biaf.repository.MovieRepository;
import com.biaf.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

	private final MovieRepository movieRepository;
	private final ReservationRepository reservationRepository;
	private final MemberRepository memberRepository;
	
	public void saveNumber(ReservationFormDto reserDto, Principal principal) {
		Movie movie = movieRepository.findById(reserDto.getMovieId()).orElseThrow(EntityNotFoundException::new);
		Member member = memberRepository.findByMemberEmail(principal.getName());
		Reservation reservation = Reservation.createNumber(reserDto, movie, member);
		reservationRepository.save(reservation);
	}

	public List<ReservationFormDto> findAll(String name) {
		return ReservationFormDto.createReservationFormDto(reservationRepository.findAllByMemberEmail(name));
	}

	public void deleteres(Long Id) { // 예매취소

		Reservation reservation = reservationRepository.findById(Id).orElseThrow(EntityNotFoundException::new);
		reservation.cancle();
	}
	
	public Page<Reservation> reser(Pageable pageable){ //예매조회,페이징
		return reservationRepository.findAll(pageable);
	 }
}
