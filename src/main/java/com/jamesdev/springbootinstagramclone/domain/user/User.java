package com.jamesdev.springbootinstagramclone.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer id;

      @Column(nullable = false,length = 20,unique = true)
      private String username;

      @Column(nullable = false,unique = true)
      private String email;

      @Column(nullable = false)
      private String password;

      @Column(nullable = false)
      private String name;

      private String website;
      private String bio;
      private String phone;
      private String profileImageUrl;

      @Enumerated(EnumType.STRING)
      private Gender gender;

      @Enumerated(EnumType.STRING)
      private RoleType role;

      private LocalDateTime createTime;

      @PrePersist
      public void createDate(){
            this.createTime= LocalDateTime.now();
      }


}
