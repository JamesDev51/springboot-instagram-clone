package com.jamesdev.springbootinstagramclone.domain.image;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jamesdev.springbootinstagramclone.domain.likes.Likes;
import com.jamesdev.springbootinstagramclone.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Image {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int id;

      private String caption;
      private String postImageUrl;

      @JsonIgnoreProperties({"images"})
      @JoinColumn(name = "userId")
      @ManyToOne
      private User user;

      //이미지 좋아요
      @JsonIgnoreProperties({"image"})
      @OneToMany(mappedBy = "image")
      private List<Likes> likes;

      //이미지 댓글

}
