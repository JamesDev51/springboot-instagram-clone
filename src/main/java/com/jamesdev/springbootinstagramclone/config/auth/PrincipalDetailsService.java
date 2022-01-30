package com.jamesdev.springbootinstagramclone.config.auth;

import com.jamesdev.springbootinstagramclone.domain.user.User;
import com.jamesdev.springbootinstagramclone.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService  implements UserDetailsService {

      private final UserRepository userRepository;

      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User userEntity = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다."));
            return new PrincipalDetails(userEntity);
      }
}
