package com.jamesdev.springbootinstagramclone.service;

import com.jamesdev.springbootinstagramclone.domain.user.User;
import com.jamesdev.springbootinstagramclone.domain.user.UserRepository;
import com.jamesdev.springbootinstagramclone.dto.user.UsernameDupCheckDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ObjectUtils;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserService {

      private final UserRepository userRepository;

      public boolean isUsernameAvailable(UsernameDupCheckDto usernameDupCheckDto){
            String username= usernameDupCheckDto.getUsername();
            return userRepository.findByUsername(username).isPresent();
      }

}
