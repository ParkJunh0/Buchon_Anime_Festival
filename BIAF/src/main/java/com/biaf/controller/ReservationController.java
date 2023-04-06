package com.biaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biaf.service.MovieService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/ko")
public class ReservationController{
	
	private final MovieService movieService;
	
	@GetMapping(value="/schedule") //현재 상영작
	public String schedule(Model model) {
		model.addAttribute("movieResponseDto", movieService.findAll());
		return "reservation/reservation";
	}
	
	@GetMapping(value="/reservation/detail/{movieId}") // 영화 상세페이지
	public String reservationdetail(@PathVariable Long movieId, Model model){
	   	model.addAttribute("movieResponseDto", movieService.findAll());
	   	model.addAttribute("movieids", movieId);
	   	return "reservation/reservation-detail";
	}
	
    @GetMapping(value="/ticket_reservation")
    public String ticket_reservation(){
        return "";
    }
    @GetMapping(value="/ticket_information")
    public String qna(){
        return "";
    }
    
}
