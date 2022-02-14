package com.jamesdev.springbootinstagramclone.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailDupCheckDto {
      @NotBlank(message="이메일은 필수 입력 값입니다.")
      @Email(message="이메일 형식에 맞지 않습니다.")
      private String email;
}
