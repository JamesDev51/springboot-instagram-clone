package com.jamesdev.springbootinstagramclone.controller;

import com.jamesdev.springbootinstagramclone.config.auth.PrincipalDetails;
import com.jamesdev.springbootinstagramclone.dto.user.UserProfileDto;
import com.jamesdev.springbootinstagramclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class UserController {

      private final UserService userService;

      @GetMapping("/user/update")
      public String update(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
            model.addAttribute("principal",principalDetails);
            return "user/update";
      }
      @GetMapping("/user/{pageUserId}")
      public String profile(@PathVariable int pageUserId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
            int principalId = principalDetails.getId();
            UserProfileDto userProfileDto = userService.getUserProfile(pageUserId, principalId);
            model.addAttribute("dto",userProfileDto);
            System.out.println("dto : "+userProfileDto);
            return "user/profile";
      }
}
