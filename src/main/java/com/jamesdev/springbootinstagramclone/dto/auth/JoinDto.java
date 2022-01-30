package com.jamesdev.springbootinstagramclone.dto.auth;

import com.jamesdev.springbootinstagramclone.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinDto {
      //TODO : VALIDATION CHECK
      private String username;
      private String password1;
      private String password2;
      private String email;
      private String name;

      public User toEntity(String encPassword){
            return User.builder()
                        .username(username)
                        .password(encPassword)
                        .email(email)
                        .name(name)
                        .build();
      }
}
