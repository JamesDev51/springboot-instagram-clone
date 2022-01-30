package com.jamesdev.springbootinstagramclone.config.auth;

import com.jamesdev.springbootinstagramclone.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@Getter
public class PrincipalDetails implements UserDetails {

      private User user;

      public PrincipalDetails(User user){
            this.user=user;
      }

      //TODO:
      @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {
            Collection<GrantedAuthority> collections=new ArrayList<>();
            collections.add(()-> "ROLE_"+user.getRole());
            return collections;
      }

      @Override
      public String getPassword() {
            return user.getPassword();
      }

      @Override
      public String getUsername() {
            return user.getUsername();
      }

      @Override
      public boolean isAccountNonExpired() {
            return true;
      }

      @Override
      public boolean isAccountNonLocked() {
            return true;
      }

      @Override
      public boolean isCredentialsNonExpired() {
            return true;
      }

      @Override
      public boolean isEnabled() {
            return true;
      }
}
