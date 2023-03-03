package com.biaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/ko")
public class FooterController {

   
   @GetMapping(value="/policy")
   public String policy() {
      return "FooterTop/policy";
   }
   
   @GetMapping(value="/pri_sta")
   public String pri_sta() {
      return "FooterTop/pri_sta";
            
   }
   @GetMapping(value="/email_rejection")
   public String email_rejection() {
      return "FooterTop/email_rejection";
   }
}
