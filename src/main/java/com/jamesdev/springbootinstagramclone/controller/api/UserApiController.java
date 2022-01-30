package com.jamesdev.springbootinstagramclone.controller.api;

import com.jamesdev.springbootinstagramclone.config.auth.PrincipalDetails;
import com.jamesdev.springbootinstagramclone.domain.user.User;
import com.jamesdev.springbootinstagramclone.dto.ResponseDto;
import com.jamesdev.springbootinstagramclone.dto.auth.EmailDupCheckDto;
import com.jamesdev.springbootinstagramclone.dto.auth.JoinDto;
import com.jamesdev.springbootinstagramclone.dto.auth.UsernameDupCheckDto;
import com.jamesdev.springbootinstagramclone.dto.user.UserUpdateDto;
import com.jamesdev.springbootinstagramclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {

      private final UserService userService;

      @PostMapping("/auth/api/checkUsernameUsed")
      public ResponseEntity<?> checkUsernameUsed(@RequestBody UsernameDupCheckDto usernameDupCheckDto){
            if(userService.isUsernameAvailable(usernameDupCheckDto)) return new ResponseEntity<>(new ResponseDto<>("이미 사용중인 아이디입니다."),HttpStatus.CONFLICT);
            else return new ResponseEntity<>(new ResponseDto<>("사용 가능한 아이디입니다. 사용하시겠습니까?"),HttpStatus.OK);
      }
      @PostMapping("/auth/api/checkEmailUsed")
      public ResponseEntity<?> checkEmailUsed(@RequestBody EmailDupCheckDto emailDupCheckDto){
            if(userService.isEmailAvailable(emailDupCheckDto)) return new ResponseEntity<>(new ResponseDto<>("이미 사용중인 이메일입니다."),HttpStatus.CONFLICT);
            else return new ResponseEntity<>(new ResponseDto<>("사용 가능한 이메일입니다. 사용하시겠습니까?"),HttpStatus.OK);
      }
      @PostMapping("/auth/api/join")
      public ResponseEntity<?> join(@RequestBody JoinDto joinDto){
            if(userService.signUp(joinDto)) return new ResponseEntity<>(new ResponseDto<>("회원가입이 완료되었습니다."),HttpStatus.CREATED);
            return new ResponseEntity<>(new ResponseDto<>("회원가입에 실패하였습니다."),HttpStatus.BAD_REQUEST);
      }

      @PostMapping("/api/user/{id}")
      public ResponseEntity<?> editUser(@PathVariable int id,
                                        @RequestBody UserUpdateDto userUpdateDto,
                                        @AuthenticationPrincipal PrincipalDetails principalDetails){
            User userEntity = userService.editUser(id,userUpdateDto);
            principalDetails.setUser(userEntity);
            return new ResponseEntity<>(new ResponseDto<>(userEntity),HttpStatus.OK);
      }

}
