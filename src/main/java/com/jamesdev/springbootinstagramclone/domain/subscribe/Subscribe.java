package com.jamesdev.springbootinstagramclone.domain.subscribe;

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
@Table(
            uniqueConstraints = {
                        @UniqueConstraint(
                                    name = "subscribe_uk",
                                    columnNames = {"fromUserId","toUserId"}
                        )
            }
)
public class Subscribe {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int id;

      @JoinColumn(name="fromUserId")
      @ManyToOne
      private User fromUser;

      @JoinColumn(name="toUserId")
      @ManyToOne
      private User toUser;

      private LocalDateTime createDate;

      @PrePersist
      public void createDate(){
            this.createDate=LocalDateTime.now();
      }

}
