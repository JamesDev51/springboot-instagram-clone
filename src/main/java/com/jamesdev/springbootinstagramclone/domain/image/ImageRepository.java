package com.jamesdev.springbootinstagramclone.domain.image;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImageRepository extends JpaRepository<Image,Integer> {

      @Query(value="select * from image where userId IN (SELECT toUserId FROM subscribe WHERE fromUserId=:principalId) ",nativeQuery = true)
      Page<Image> story(int principalId, Pageable pageable);
}
