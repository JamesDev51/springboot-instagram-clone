package com.jamesdev.springbootinstagramclone.controller.api;

import com.jamesdev.springbootinstagramclone.dto.ResponseDto;
import com.jamesdev.springbootinstagramclone.dto.user.UsernameDupCheckDto;
import com.jamesdev.springbootinstagramclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {

      private final UserService userService;

      @PostMapping("/auth/api/checkUsernameUsed")
      public ResponseEntity<?> checkUsernameUsed(UsernameDupCheckDto usernameDupCheckDto){
            if(userService.isUsernameAvailable(usernameDupCheckDto)) return new ResponseEntity<>(new ResponseDto<>("이미 사용중인 아이디입니다."),HttpStatus.CONFLICT);
            else return new ResponseEntity<>(new ResponseDto<>("사용 가능한 아이디입니다. 사용하시겠습니까?"),HttpStatus.OK);
      }
}
