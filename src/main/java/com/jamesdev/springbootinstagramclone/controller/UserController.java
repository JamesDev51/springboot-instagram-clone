package com.jamesdev.springbootinstagramclone.controller;

import com.jamesdev.springbootinstagramclone.config.auth.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

      @GetMapping("/join")
      public String join(){
            return "auth/join";
      }

      @GetMapping("/login")
      public String login(){
            return "auth/login";
      }

      @GetMapping("/user/update")
      public String update(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
            System.out.println("principal : "+principalDetails);
            model.addAttribute("principal",principalDetails);
            return "user/update";
      }
}
