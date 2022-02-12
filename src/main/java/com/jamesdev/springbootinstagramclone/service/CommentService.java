package com.jamesdev.springbootinstagramclone.service;

import com.jamesdev.springbootinstagramclone.domain.comment.Comment;
import com.jamesdev.springbootinstagramclone.domain.comment.CommentRepository;
import com.jamesdev.springbootinstagramclone.domain.image.Image;
import com.jamesdev.springbootinstagramclone.domain.user.User;
import com.jamesdev.springbootinstagramclone.domain.user.UserRepository;
import com.jamesdev.springbootinstagramclone.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

      private final CommentRepository commentRepository;
      private final UserRepository userRepository;

      @Transactional
      public Comment writeComment(String content,int imageId,int userId){
            Image image = new Image();
            User userEntity = userRepository.findById(userId).orElseThrow(()->{
                  throw new CustomApiException("유저 아이디를 찾을 수 없습니다.");
            });
            image.setId(imageId);

            Comment comment = new Comment();
            comment.setContent(content);
            comment.setUser(userEntity);
            comment.setImage(image);
            return commentRepository.save(comment);
      }
}
