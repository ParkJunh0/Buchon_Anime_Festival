package com.biaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/ko")
public class EventController {
    @GetMapping(value="/eventhall_info")
    public String eventhall_info(){
        return "";
    }
    @GetMapping(value="/navigation")
    public String navigation(){
        return "";
    }
    @GetMapping(value="/opening_ceremony")
    public String opening_ceremony(){
        return "";
    }
    @GetMapping(value="/exhibition")
    public String exhibition(){
        return "";
    }
    @GetMapping(value="/incidental_event")
    public String incidental_event(){
        return "";
    }
    @GetMapping(value="/contest")
    public String contest(){
        return "";
    }
}
