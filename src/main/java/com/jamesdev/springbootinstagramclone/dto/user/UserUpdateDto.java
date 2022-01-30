package com.jamesdev.springbootinstagramclone.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {
      String name;
      String password;
      String website;
      String bio;
      String email;
      String phone;
}
