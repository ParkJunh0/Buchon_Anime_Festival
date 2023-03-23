package com.biaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/ko")
public class EventController {
    @GetMapping(value="/eventhall_info")
    public String eventhall_info(){
        return "Event/venue";
    }
    @GetMapping(value="/navigation")
    public String navigation(){
        return "Event/loadmap";
    }
    @GetMapping(value="/opening_ceremony")
    public String opening_ceremony(){
        return "Event/opening";
    }
    @GetMapping(value="/exhibition")
    public String exhibition(){
        return "Event/exhibition";
    }
    @GetMapping(value="/incidental_event")
    public String incidental_event(){
        return "Event/incidental_event";
    }
    @GetMapping(value="/incidental_event2")
    public String incidental_event2(){
        return "Event/incidental_event2";
    }
    @GetMapping(value="/contest")
    public String contest(){
        return "Event/contest";
    }
    @GetMapping(value="/contest2")
    public String contest2(){
        return "Event/contest2";
    }
}
