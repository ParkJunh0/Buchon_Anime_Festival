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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.biaf.dto.ReservationFormDto;
import com.biaf.service.MovieService;
import com.biaf.service.ReservationService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/ko")
public class ReservationController {
	private final MovieService movieService;
	private final ReservationService reservationService;

	@GetMapping(value = "/schedule") // 현재 상영작
	public String schedule(Model model) {
		model.addAttribute("movieResponseDto", movieService.findAll());
		return "reservation/reservation";
	}

	@GetMapping(value = "/reservation/detail/{movieId}") // 영화 상세페이지
	public String reservationdetail(@PathVariable Long movieId, Model model) {
		model.addAttribute("movieResponseDto", movieService.findAll());
		model.addAttribute("movieids", movieId);
		return "reservation/reservation-detail";
	}

	@GetMapping(value = "/reservationlist") // 회원 티켓예매내역
	public String reservationlist(Model model, Principal principal) {
		List<ReservationFormDto> reserformDto = reservationService.findAll(principal.getName()); 
	
		
	    model.addAttribute("reservationFormDto", reserformDto);
	    int statok=0;
	    int statcan=0;
	    for(ReservationFormDto reser : reserformDto) {
	    	if(reser.getStatus().name() == "CANCLE") {
	    		statcan=1;
	    	}
	    	if(reser.getStatus().name() == "OK") {
	    		statok=1;
	    	}
	    }
	    model.addAttribute("statok", statok);
	    model.addAttribute("statcan", statcan);
	    return "member/reservationlist";
	}
	
	@PostMapping(value="/reservationlist/delete") //티켓예매취소
	public @ResponseBody ResponseEntity reservationdelete(@RequestParam Long Id) {
	reservationService.deleteres(Id);
	return new ResponseEntity<Long>(1L,HttpStatus.OK);
	}

	@PostMapping(value = "/reservation_num") //예매페이지
	public @ResponseBody ResponseEntity makeReservationNum(@Valid ReservationFormDto reservationFormDto,
			BindingResult bindingResult, Model model, Principal principal) {

		if (bindingResult.hasErrors()) {
			StringBuilder sb = new StringBuilder();
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();

			for (FieldError fieldError : fieldErrors) {
				sb.append(fieldError.getDefaultMessage());
			}
			return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
		}
		try {

			reservationService.saveNumber(reservationFormDto, principal);

		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return new ResponseEntity<String>("최소 1개 이상 담아주세요", HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<Long>(1L,HttpStatus.OK);
	}
	 @GetMapping(value="/ticket_information") //티켓 안내
     public String ticket_information(){
         return "reservation/ticket_information";
     }
}
