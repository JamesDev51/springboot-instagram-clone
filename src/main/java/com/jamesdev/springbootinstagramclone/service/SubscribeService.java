package com.jamesdev.springbootinstagramclone.service;

import com.jamesdev.springbootinstagramclone.domain.subscribe.SubscribeRepository;
import com.jamesdev.springbootinstagramclone.dto.subscribe.SubscribeDto;
import com.jamesdev.springbootinstagramclone.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Service

public class SubscribeService {

      private final SubscribeRepository subscribeRepository;
      private final EntityManager em;

      @Transactional
      public void subscribe(int fromUserId, int toUserId){
            try{
                  subscribeRepository.subscribe(fromUserId,toUserId);
            }catch (Exception e){
                  throw new CustomApiException("이미 구독을 하였습니다.");
            }
      }
      //TODO : sql 부분 공부하고 이 부분 다시 공부해보기
      @Transactional(readOnly = true)
      public List<SubscribeDto> getSubscribeList(int principalId, int pageUserId){
            StringBuffer sb = new StringBuffer();
            System.out.println("principalId : "+ principalId + " pageUserId : "+pageUserId);
            sb.append("select u.id, u.username, u.profileImageUrl, ");
            sb.append("if((select 1 from subscribe where fromUserId=? and toUserId=u.id),1,0) subscribeState, ");
            sb.append("if((?=u.id),1,0) equalUserState ");
            sb.append("from user u INNER JOIN subscribe s ");
            sb.append("ON u.id = s.fromUserId ");
            sb.append("WHERE s.toUserId=?"); //세미콜론 첨부하면 안됨

            Query query =em.createNativeQuery(sb.toString())
                        .setParameter(1,principalId)
                        .setParameter(2,principalId)
                        .setParameter(3,pageUserId);

            JpaResultMapper resultMapper = new JpaResultMapper();
            List<SubscribeDto> subscribeDtoList = resultMapper.list(query,SubscribeDto.class);
            for (SubscribeDto subscribeDto : subscribeDtoList) {
                  System.out.println("subscribeDto : "+subscribeDto);
            }
            return subscribeDtoList;
      }


      @Transactional
      public void unsubscribe(int fromUserId, int toUserId) {
            subscribeRepository.unsubscribe(fromUserId,toUserId);
      }
}
