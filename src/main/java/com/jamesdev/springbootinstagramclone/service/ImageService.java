package com.jamesdev.springbootinstagramclone.service;

import com.jamesdev.springbootinstagramclone.config.auth.PrincipalDetails;
import com.jamesdev.springbootinstagramclone.domain.image.Image;
import com.jamesdev.springbootinstagramclone.domain.image.ImageRepository;
import com.jamesdev.springbootinstagramclone.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

      private final ImageRepository imageRepository;

      @Value("${file.path}")
      private String uploadFolder;

      public void uploadImage(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails){

            UUID uuid = UUID.randomUUID();
            String imageFileName = uuid + "_"+imageUploadDto.getFile().getOriginalFilename();
            System.out.println("이미지 파일 이름 : "+imageFileName);

            Path imageFilePath = Paths.get(uploadFolder+imageFileName);

            try {
                  Files.write(imageFilePath,imageUploadDto.getFile().getBytes());
            } catch (IOException e) {
                  e.printStackTrace();
            }
            Image image = imageUploadDto.toEntity(imageFileName,principalDetails.getUser());
            imageRepository.save(image);
      }

}
