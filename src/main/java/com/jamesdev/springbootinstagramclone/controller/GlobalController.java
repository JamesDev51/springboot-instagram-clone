package com.jamesdev.springbootinstagramclone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GlobalController {
      @GetMapping("/join")
      public String join(){
            return "auth/join";
      }

      @GetMapping("/login")
      public String login(){
            return "auth/login";
      }
}
