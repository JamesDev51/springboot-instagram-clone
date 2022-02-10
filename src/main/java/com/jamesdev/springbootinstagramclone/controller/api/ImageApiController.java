package com.jamesdev.springbootinstagramclone.controller.api;

import com.jamesdev.springbootinstagramclone.config.auth.PrincipalDetails;
import com.jamesdev.springbootinstagramclone.domain.image.Image;
import com.jamesdev.springbootinstagramclone.dto.ResponseDto;
import com.jamesdev.springbootinstagramclone.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RequiredArgsConstructor
@RestController
public class ImageApiController {

      private final ImageService imageService;

      @GetMapping("/api/image")
      public ResponseEntity<?> getStory(@AuthenticationPrincipal PrincipalDetails principalDetails, @PageableDefault(size=5) Pageable pageable){
            Page<Image> images = imageService.getStory(principalDetails.getId(),pageable);
            System.out.println("images : "+images);
            return new ResponseEntity<>(new ResponseDto<>(images), HttpStatus.OK);

      }
}
