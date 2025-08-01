package com.ds04011.widbuddy.joinflag.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.ds04011.widbuddy.post.domain.Post;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="`joinflag`")
@Getter
@Setter
@Builder(toBuilder=true)
@AllArgsConstructor                              
@NoArgsConstructor 
public class Joinflag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long userId;

    private int headcount;
    private int currentNumber = 0;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "postId") // 게시글이 생성 된 이후에, 그 정보를 가지고 와서 이 정보를 저장해야함, 
    private Post post; 	// 객체 지향적으로 관계를 생각해야해서, 단순하게 postId 가 아니라 그 객체 Post 를 보는거, 
}
//public class Joinflag {
//
//@Id
//@GeneratedValue(strategy=GenerationType.IDENTITY)
//private long id;
//
//private long userId;
//private long postId;
//private int headcount;
//private int currentNumber = 0;
//
//@CreationTimestamp
//private LocalDateTime createdAt;
//
//}

