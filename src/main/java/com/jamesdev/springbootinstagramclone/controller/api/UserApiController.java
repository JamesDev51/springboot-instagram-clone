package com.jamesdev.springbootinstagramclone.controller.api;

import com.jamesdev.springbootinstagramclone.dto.ResponseDto;
import com.jamesdev.springbootinstagramclone.dto.user.UsernameDupCheckDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {
      public ResponseEntity<?> checkUsernameUsed(UsernameDupCheckDto usernameDupCheckDto){
            return null;
      }
}
