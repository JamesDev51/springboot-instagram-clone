package com.jamesdev.springbootinstagramclone.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {

      @NotBlank
      String name;

      String password;

      String website;

      String bio;

      @NotBlank(message="이메일은 필수 입력 값입니다.")
      @Email(message="이메일 형식에 맞지 않습니다.")
      String email;
      String phone;
}
