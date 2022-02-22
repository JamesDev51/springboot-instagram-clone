package com.jamesdev.springbootinstagramclone.domain.image;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Integer> {

      @Query(value="select * from image where userId IN (SELECT toUserId FROM subscribe WHERE fromUserId=:principalId) ",nativeQuery = true)
      Page<Image> story(int principalId, Pageable pageable);

      @Query(value = "SELECT i.* FROM image i INNER JOIN (select imageId, COUNT(imageid) likeCount from likes group by imageId)c ON i.id=c.imageId ORDER BY likeCount DESC", nativeQuery = true)
      List<Image> popularImages();
}
