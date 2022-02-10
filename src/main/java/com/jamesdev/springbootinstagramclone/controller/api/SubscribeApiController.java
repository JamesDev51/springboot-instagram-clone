package com.jamesdev.springbootinstagramclone.controller.api;

import com.jamesdev.springbootinstagramclone.config.auth.PrincipalDetails;
import com.jamesdev.springbootinstagramclone.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class SubscribeApiController {

      private final SubscribeService subscribeService;

      @PostMapping("/api/subscribe/{toUserId}")
      public ResponseEntity<?> subscribe(
                  @PathVariable int toUserId,
                  @AuthenticationPrincipal PrincipalDetails principalDetails){
            int fromUserId=principalDetails.getId();
            System.out.println("toUserId : "+toUserId);
            System.out.println("fromUserId : "+fromUserId);
            subscribeService.subscribe(fromUserId,toUserId);
            return null;
      }
      @DeleteMapping("/api/subscribe/{toUserId}")
      public ResponseEntity<?> unsubscribe(
                  @PathVariable int toUserId,
                  @AuthenticationPrincipal PrincipalDetails principalDetails){
            int fromUserId=principalDetails.getId();
            System.out.println("toUserId : "+toUserId);
            System.out.println("fromUserId : "+fromUserId);
            subscribeService.unsubscribe(fromUserId,toUserId);
            return null;
      }



}
