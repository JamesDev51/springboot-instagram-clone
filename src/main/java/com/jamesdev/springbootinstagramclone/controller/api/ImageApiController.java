package com.jamesdev.springbootinstagramclone.controller.api;

import com.jamesdev.springbootinstagramclone.config.auth.PrincipalDetails;
import com.jamesdev.springbootinstagramclone.domain.image.Image;
import com.jamesdev.springbootinstagramclone.dto.ResponseDto;
import com.jamesdev.springbootinstagramclone.service.ImageService;
import com.jamesdev.springbootinstagramclone.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RequiredArgsConstructor
@RestController
public class ImageApiController {

      private final ImageService imageService;
      private final LikesService likesService;

      @GetMapping("/api/image")
      public ResponseEntity<?> getStory(@AuthenticationPrincipal PrincipalDetails principalDetails, @PageableDefault(size=5) Pageable pageable){
            Page<Image> images = imageService.getStory(principalDetails.getId(),pageable);
            System.out.println("images : "+images);
            return new ResponseEntity<>(new ResponseDto<>(images), HttpStatus.OK);
      }

      @PostMapping("/api/image/{imageId}/likes")
      public ResponseEntity<?> like(@PathVariable int imageId, @AuthenticationPrincipal PrincipalDetails principalDetails){
            likesService.like(imageId,principalDetails.getId());
            return new ResponseEntity<>(new ResponseDto<>("좋아요 성공"),HttpStatus.CREATED);
      }

      @DeleteMapping("/api/image/{imageId}/likes")
      public ResponseEntity<?> unlike(@PathVariable int imageId, @AuthenticationPrincipal PrincipalDetails principalDetails){
            likesService.unlike(imageId,principalDetails.getId());
            return new ResponseEntity<>(new ResponseDto<>("좋아요 취소 성공"),HttpStatus.OK);
      }

}
