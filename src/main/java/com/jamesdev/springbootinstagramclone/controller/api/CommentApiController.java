package com.jamesdev.springbootinstagramclone.controller.api;

import com.jamesdev.springbootinstagramclone.config.auth.PrincipalDetails;
import com.jamesdev.springbootinstagramclone.domain.comment.Comment;
import com.jamesdev.springbootinstagramclone.dto.ResponseDto;
import com.jamesdev.springbootinstagramclone.dto.comment.CommentDto;
import com.jamesdev.springbootinstagramclone.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

      private final CommentService commentService;

      @PostMapping("/api/comment")
      public ResponseEntity<?> saveComment(@Validated @RequestBody CommentDto commentDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails){

            Comment comment = commentService.writeComment(commentDto.getContent(),commentDto.getImageId(),principalDetails.getId());
            return new ResponseEntity<>(new ResponseDto<>(comment), HttpStatus.CREATED);
      }


}
