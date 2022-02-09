package com.jamesdev.springbootinstagramclone.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.ManyToOne;

public interface SubscribeRepository extends JpaRepository<Subscribe,Integer> {

      @Modifying
      @Query(value="INSERT INTO subscribe(fromUserId,toUserId,createDate) VALUES (:fromUserId,:toUserId, now())",nativeQuery = true)
      void subscribe(int fromUserId, int toUserId);

      @Query(value= "SELECT COUNT(*) FROM subscribe WHERE fromUserId=:principalId AND toUserId=:pageUserId",nativeQuery = true)
      int subscribeState(int principalId, int pageUserId);

      @Query(value="SELECT COUNT(*) FROM subscribe WHERE toUserId=:pageUserId",nativeQuery = true)
      int subscribeCount(int pageUserId);
}
