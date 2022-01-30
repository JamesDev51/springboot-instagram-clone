package com.jamesdev.springbootinstagramclone.service;

import com.jamesdev.springbootinstagramclone.domain.user.RoleType;
import com.jamesdev.springbootinstagramclone.domain.user.User;
import com.jamesdev.springbootinstagramclone.domain.user.UserRepository;
import com.jamesdev.springbootinstagramclone.dto.auth.EmailDupCheckDto;
import com.jamesdev.springbootinstagramclone.dto.auth.JoinDto;
import com.jamesdev.springbootinstagramclone.dto.auth.UsernameDupCheckDto;
import com.jamesdev.springbootinstagramclone.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserService {

      private final UserRepository userRepository;
      private final BCryptPasswordEncoder bCryptPasswordEncoder;

      @Transactional(readOnly = true)
      public boolean isUsernameAvailable(UsernameDupCheckDto usernameDupCheckDto){
            String username= usernameDupCheckDto.getUsername();
            return userRepository.findByUsername(username).isPresent();
      }

      @Transactional(readOnly = true)
      public boolean isEmailAvailable(EmailDupCheckDto emailDupCheckDto) {
            String email= emailDupCheckDto.getEmail();
            return userRepository.findByEmail(email).isPresent();
      }

      private boolean isPasswordEqual(JoinDto joinDto){
            return Objects.equals(joinDto.getPassword1(), joinDto.getPassword2());
      }

      @Transactional
      public boolean signUp(JoinDto joinDto){
            //TODO: 권한설정 (USER, ADMIN) 지금은 그냥 USER
            if (!isPasswordEqual(joinDto)) return false;
            String rawPassword=joinDto.getPassword1();
            String encPassword = bCryptPasswordEncoder.encode(rawPassword);
            User newUser=joinDto.toEntity(encPassword);
            newUser.setRole(RoleType.USER);
            userRepository.save(newUser);
            return true;

      }


      @Transactional
      public User editUser(int id, UserUpdateDto userUpdateDto) {
            User userEntity = userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("찾을 수 없음"));

            String rawPassword = userUpdateDto.getPassword();
            String encPassword= Objects.equals(rawPassword, "") ?userEntity.getPassword():bCryptPasswordEncoder.encode(rawPassword); //비었으면 원래 비밀번호, 있으면 인코딩

            userEntity.setPassword(encPassword);
            userEntity.setName(userUpdateDto.getName());
            userEntity.setBio(userUpdateDto.getBio());
            userEntity.setWebsite(userUpdateDto.getWebsite());
            userEntity.setPhone(userUpdateDto.getPhone());
            return userEntity;
      }
}
