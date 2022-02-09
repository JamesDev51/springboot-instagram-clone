package com.jamesdev.springbootinstagramclone.controller;

import com.jamesdev.springbootinstagramclone.config.auth.PrincipalDetails;
import com.jamesdev.springbootinstagramclone.dto.image.ImageUploadDto;
import com.jamesdev.springbootinstagramclone.handler.ex.CustomValidationException;
import com.jamesdev.springbootinstagramclone.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ImageController {

      private final ImageService imageService;

      @GetMapping("/image/upload")
      public String upload(){
            return "image/upload";
      }
      @PostMapping("/image")
      public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
            if(imageUploadDto.getFile().isEmpty()){
                  throw new CustomValidationException("이미지가 첨부되지 않았습니다.");
            }
            imageService.uploadImage(imageUploadDto,principalDetails);
            return "redirect:/user/"+principalDetails.getId();
      }
}
