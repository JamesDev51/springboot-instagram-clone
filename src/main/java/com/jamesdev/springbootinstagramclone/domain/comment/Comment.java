package com.jamesdev.springbootinstagramclone.domain.comment;

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
public class Comment {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int id;

      @Column(length = 100,nullable = false)
      private  String content;

      @JsonIgnoreProperties({"images"})
      @JoinColumn(name="userId")
      @ManyToOne(fetch = FetchType.EAGER)
      private User user;

      @JoinColumn(name="imageId")
      @ManyToOne(fetch = FetchType.EAGER)
      private Image image;

      private LocalDateTime createDate;

      @PrePersist
      public void createDate(){
            this.createDate=LocalDateTime.now();
      }
}
