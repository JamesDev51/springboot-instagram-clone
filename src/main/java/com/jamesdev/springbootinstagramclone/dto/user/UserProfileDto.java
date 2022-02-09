package com.jamesdev.springbootinstagramclone.dto.user;

import com.jamesdev.springbootinstagramclone.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {
      private boolean pageOwnerState;
      private int imageCount;
      private boolean subscribeState;
      private int subscribeCount;
      private User user;
}
