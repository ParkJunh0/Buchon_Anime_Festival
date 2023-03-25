package com.biaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biaf.service.MovieService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/ko")
public class ReservationController{
	
	private final MovieService movieService;
	
	@GetMapping(value="/schedule")
	public String schedule(Model model) {
		model.addAttribute("movieResponseDto", movieService.findAll());
		return "reservation/reservation";
	}
	
	@GetMapping(value="/reservation_sub")
	public String reservation_sub(){
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
