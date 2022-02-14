package com.jamesdev.springbootinstagramclone.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jamesdev.springbootinstagramclone.domain.image.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

      @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
      @JsonIgnoreProperties({"user"})
      private List<Image> images;

      @PrePersist
      public void createDate(){
            this.createTime= LocalDateTime.now();
      }

      @Override
      public String toString() {
            return "User{" +
                        "id=" + id +
                        ", username='" + username + '\'' +
                        ", email='" + email + '\'' +
                        ", password='" + password + '\'' +
                        ", name='" + name + '\'' +
                        ", website='" + website + '\'' +
                        ", bio='" + bio + '\'' +
                        ", phone='" + phone + '\'' +
                        ", profileImageUrl='" + profileImageUrl + '\'' +
                        ", gender=" + gender +
                        ", role=" + role +
                        ", createTime=" + createTime +
                        '}';
      }
}
