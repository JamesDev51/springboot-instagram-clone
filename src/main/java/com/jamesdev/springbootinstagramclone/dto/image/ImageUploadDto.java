package com.jamesdev.springbootinstagramclone.dto.image;

import com.jamesdev.springbootinstagramclone.domain.image.Image;
import com.jamesdev.springbootinstagramclone.domain.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadDto {

      private MultipartFile file;
      private String caption;

      public Image toEntity(String postImageUrl, User user){
            return Image.builder()
                        .caption(caption)
                        .postImageUrl("/upload/"+postImageUrl)
                        .user(user)
                        .build();
      }
}
