package com.biaf.service;

import java.security.Principal;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

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
	
	public void saveNumber(ReservationFormDto reserDto,Principal principal) {
		Movie movie = movieRepository.findById(reserDto.getMovieId()).orElseThrow(EntityNotFoundException::new);
		Member member = memberRepository.findByMemberEmail(principal.getName());
		Reservation reservation =Reservation.createNumber(reserDto,movie,member);
		System.out.println(reservation);
		reservationRepository.save(reservation);
	}
	

	
	
}
