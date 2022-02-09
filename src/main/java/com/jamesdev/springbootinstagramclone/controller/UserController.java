package com.jamesdev.springbootinstagramclone.controller;

import com.jamesdev.springbootinstagramclone.config.auth.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
      @GetMapping("/user/update")
      public String update(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
            model.addAttribute("principal",principalDetails);
            return "user/update";
      }
      @GetMapping("/user/{pageUserId}")
      public String profile(@PathVariable int pageUserId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
//            UserPorifleDto
            return null;
      }
}
