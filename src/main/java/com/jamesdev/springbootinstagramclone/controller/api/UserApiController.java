package com.jamesdev.springbootinstagramclone.controller.api;

import com.jamesdev.springbootinstagramclone.config.auth.PrincipalDetails;
import com.jamesdev.springbootinstagramclone.domain.user.User;
import com.jamesdev.springbootinstagramclone.dto.ResponseDto;
import com.jamesdev.springbootinstagramclone.dto.auth.EmailDupCheckDto;
import com.jamesdev.springbootinstagramclone.dto.auth.JoinDto;
import com.jamesdev.springbootinstagramclone.dto.auth.UsernameDupCheckDto;
import com.jamesdev.springbootinstagramclone.dto.subscribe.SubscribeDto;
import com.jamesdev.springbootinstagramclone.dto.user.UserUpdateDto;
import com.jamesdev.springbootinstagramclone.service.SubscribeService;
import com.jamesdev.springbootinstagramclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserApiController {

      private final UserService userService;
      private final SubscribeService subscribeService;

      @PostMapping("/auth/api/checkUsernameUsed")
      public ResponseEntity<?> checkUsernameUsed(@Validated @RequestBody UsernameDupCheckDto usernameDupCheckDto,BindingResult bindingResult){
            if(userService.isUsernameAvailable(usernameDupCheckDto)) return new ResponseEntity<>(new ResponseDto<>("이미 사용중인 아이디입니다."),HttpStatus.CONFLICT);
            else return new ResponseEntity<>(new ResponseDto<>("사용 가능한 아이디입니다. 사용하시겠습니까?"),HttpStatus.OK);
      }
      @PostMapping("/auth/api/checkEmailUsed")
      public ResponseEntity<?> checkEmailUsed(@Validated @RequestBody EmailDupCheckDto emailDupCheckDto ,BindingResult bindingResult){
            if(userService.isEmailAvailable(emailDupCheckDto)) return new ResponseEntity<>(new ResponseDto<>("이미 사용중인 이메일입니다."),HttpStatus.CONFLICT);
            else return new ResponseEntity<>(new ResponseDto<>("사용 가능한 이메일입니다. 사용하시겠습니까?"),HttpStatus.OK);
      }
      @PostMapping("/auth/api/join")
      public ResponseEntity<?> join(
                  @Validated @RequestBody JoinDto joinDto,
                  BindingResult bindingResult
      ){
            if(userService.signUp(joinDto)) return new ResponseEntity<>(new ResponseDto<>("회원가입이 완료되었습니다."),HttpStatus.CREATED);
            return new ResponseEntity<>(new ResponseDto<>("회원가입에 실패하였습니다."),HttpStatus.BAD_REQUEST);
      }

      @PutMapping("/api/user/{id}")
      public ResponseEntity<?> editUser(
                  @PathVariable int id,
                  @Validated  UserUpdateDto userUpdateDto,
                  BindingResult bindingResult,
                  @AuthenticationPrincipal PrincipalDetails principalDetails){
            int userId= principalDetails.getId();
            User userEntity = userService.editUser(userId,userUpdateDto);
            principalDetails.setUser(userEntity);
            return new ResponseEntity<>(new ResponseDto<>(userEntity),HttpStatus.OK);
      }

      @PutMapping("/api/user/{principalId}/profileImageUrl")
      public ResponseEntity<?> updateProfileImage(@PathVariable int principalId, MultipartFile profileImageFile){
            User userEntity = userService.updateProfileImage(principalId,profileImageFile);
            return new ResponseEntity<>(new ResponseDto<>(userEntity),HttpStatus.OK);
      }

      @GetMapping("/api/user/{pageUserId}/subscribe")
      public ResponseEntity<?> getSubscribeList(@PathVariable int pageUserId,@AuthenticationPrincipal PrincipalDetails principalDetails){
            List<SubscribeDto> subscribeDtoList = subscribeService.getSubscribeList(principalDetails.getId(),pageUserId);
            return new ResponseEntity<>(new ResponseDto<>(subscribeDtoList),HttpStatus.OK);
      }

}
