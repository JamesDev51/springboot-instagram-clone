package com.jamesdev.springbootinstagramclone.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsernameDupCheckDto {
      @NotBlank(message="아이디는 필수 입력 값입니다.")
      @Pattern(regexp = "^[a-z]+[a-z0-9]{5,19}$",message="아이디는 6~20자의 영문자로 시작하는 영문자 또는 숫자로만 입력 가능합니다.")
      private String username;
}
