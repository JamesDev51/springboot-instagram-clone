package com.jamesdev.springbootinstagramclone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {

      @GetMapping("/loginn")
      public String test2(){
            return "auth/login";
      }

      @GetMapping("/explore")
      public String test3(){
            return "post/explore";
      }

      @GetMapping("/story")
      public String test4(){
            return "post/story";
      }

      @GetMapping("/upload")
      public String test5(){
            return "post/upload";
      }

      @GetMapping("/profile")
      public String test6(){
            return "user/profile";
      }

      @GetMapping("/update")
      public String test7(){
            return "user/update";
      }



}
