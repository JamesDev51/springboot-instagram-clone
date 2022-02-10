package com.jamesdev.springbootinstagramclone.domain.likes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jamesdev.springbootinstagramclone.domain.image.Image;
import com.jamesdev.springbootinstagramclone.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Likes {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer id;

      @JoinColumn(name="imageId")
      @ManyToOne
      private Image image;

      @JsonIgnoreProperties({"images"})
      @JoinColumn(name="userId")
      @ManyToOne
      private User user;

      private LocalDateTime createDate;

      @PrePersist
      public void createDate(){
            this.createDate=LocalDateTime.now();
      }


}
