package com.jamesdev.springbootinstagramclone.dto.auth;

import com.jamesdev.springbootinstagramclone.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinDto {

      //TODO : VALIDATION CHECK
      @NotBlank(message="아이디는 필수 입력 값입니다.")
      @Pattern(regexp = "/^[a-z]+[a-z0-9]{5,19}$/g",message="아이디는 6~20자의 영문자로 시작하는 영문자 또는 숫자로만 입력 가능합니다.")
      private String username;

      @NotBlank(message="비밀번호는 필수 입력 값입니다.")
      @Pattern(regexp="/^[a-zA-Z0-9]{4,12}$/",message ="비밀번호는 4~12자의 영문 대소문자와 숫자로만 입력 가능합니다.")
      private String password1;

      @NotBlank(message="확인 비밀번호는 필수 입력 값입니다.")
      @Pattern(regexp="/^[a-zA-Z0-9]{4,12}$/",message ="비밀번호는 4~12자의 영문 대소문자와 숫자로만 입력 가능합니다.")
      private String password2;

      @NotBlank(message="이메일은 필수 입력 값입니다.")
      @Email(message="이메일 형식에 맞지 않습니다.")
      private String email;

      @NotBlank
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
