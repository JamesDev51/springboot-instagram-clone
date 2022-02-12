package com.jamesdev.springbootinstagramclone.service;

import com.jamesdev.springbootinstagramclone.domain.likes.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikesService {

      private final LikesRepository likesRepository;

      @Transactional
      public void like(int imageId, int principalId){
            likesRepository.like(imageId,principalId);
      }

      @Transactional
      public void unlike(int imageId, int principalId){
            likesRepository.unlike(imageId,principalId);
      }


}
