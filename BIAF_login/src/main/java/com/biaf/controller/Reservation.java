package com.biaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/ko")
public class Reservation {
    @GetMapping(value="/ticket_reservation")
    public String ticket_reservation(){
        return "";
    }
    @GetMapping(value="/schedule")
    public String schedule(){
        return "";
    }
    @GetMapping(value="/ticket_information")
    public String qna(){
        return "";
    }
}
