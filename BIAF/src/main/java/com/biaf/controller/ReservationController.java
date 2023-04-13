package com.biaf.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biaf.dto.ReservationFormDto;
import com.biaf.service.MovieService;
import com.biaf.service.ReservationService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/ko")
public class ReservationController{
	
	private final MovieService movieService;
	private final ReservationService reservationService;

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
	
//    @GetMapping(value="/ticket_reservation")
//    public String ticket_reservation(){
//        return "";
//    }
    @GetMapping(value="/ticket_information")
    public String qna(){
        return "reservation/ticket_information";
    }
    

    
    @PostMapping(value="/reservation_num")
    public @ResponseBody ResponseEntity makeReservationNum(@Valid ReservationFormDto reservationFormDto,BindingResult bindingResult, Model model,Principal principal) {
    
  	  if(bindingResult.hasErrors()){ 
  		StringBuilder sb = new StringBuilder();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();

		for (FieldError fieldError : fieldErrors) {
			sb.append(fieldError.getDefaultMessage());
		}
  		return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
  	} 
  	   try { 
  	   
  	   reservationService.saveNumber(reservationFormDto,principal);
  	  
  	  } catch(IllegalStateException e) {
  	  model.addAttribute("errorMessage",e.getMessage());
  	System.out.println("controller"+reservationFormDto);
  	return new ResponseEntity<String>("최소 1개 이상 담아주세요", HttpStatus.BAD_REQUEST);
  	  
  	  }
  	 return new ResponseEntity<Long>( HttpStatus.OK);
  	  }
  	  }
    
