package com.jamesdev.springbootinstagramclone.service;

import com.jamesdev.springbootinstagramclone.domain.subscribe.SubscribeRepository;
import com.jamesdev.springbootinstagramclone.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service

public class SubscribeService {

      private final SubscribeRepository subscribeRepository;

      @Transactional
      public void subscribe(int fromUserId, int toUserId){
            try{
                  subscribeRepository.subscribe(fromUserId,toUserId);
            }catch (Exception e){
                  throw new CustomApiException("이미 구독을 하였습니다.");
            }
      }
}
