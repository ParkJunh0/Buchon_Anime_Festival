package com.biaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/ko")
public class ReservationController{
	@GetMapping(value="/schedule")
	public String schedule(){
		return "reservation/reservation";
	}
	
	@GetMapping(value="/reservation_sub")
	public String reservation_sub(){
		return "reservation/reservation-detail_s";
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
