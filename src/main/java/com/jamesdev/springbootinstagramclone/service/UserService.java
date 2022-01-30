package com.jamesdev.springbootinstagramclone.service;

import com.jamesdev.springbootinstagramclone.domain.user.User;
import com.jamesdev.springbootinstagramclone.domain.user.UserRepository;
import com.jamesdev.springbootinstagramclone.dto.auth.EmailDupCheckDto;
import com.jamesdev.springbootinstagramclone.dto.auth.JoinDto;
import com.jamesdev.springbootinstagramclone.dto.auth.UsernameDupCheckDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserService {

      private final UserRepository userRepository;
      private final BCryptPasswordEncoder bCryptPasswordEncoder;

      public boolean isUsernameAvailable(UsernameDupCheckDto usernameDupCheckDto){
            String username= usernameDupCheckDto.getUsername();
            return userRepository.findByUsername(username).isPresent();
      }

      public boolean isEmailAvailable(EmailDupCheckDto emailDupCheckDto) {
            String email= emailDupCheckDto.getEmail();
            return userRepository.findByEmail(email).isPresent();
      }

      private boolean isPasswordEqual(JoinDto joinDto){
            return Objects.equals(joinDto.getPassword1(), joinDto.getPassword2());
      }

      public boolean signUp(JoinDto joinDto){
            System.out.println("joinDto : "+joinDto);
            if (!isPasswordEqual(joinDto)) return false;
            String rawPassword=joinDto.getPassword1();
            String encPassword = bCryptPasswordEncoder.encode(rawPassword);
            User newUser=joinDto.toEntity(encPassword);
            userRepository.save(newUser);
            return true;

      }
}
