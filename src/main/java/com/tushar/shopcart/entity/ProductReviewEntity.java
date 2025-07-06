package com.tushar.shopcart.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "product_reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class ProductReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_id")
    private OrderItemEntity orderItem; // To verify purchase

    @Column(nullable = false)
    private Integer rating; // 1-5

    @Column(length = 1000)
    private String reviewText;

    @Column(nullable = false)
    private Boolean isApproved = false;

    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

//    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
//    private List<ReviewHelpfulVoteEntity> helpfulVotes = new ArrayList<>();
}
//
//@Entity
//@Table(name = "review_helpful_votes")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@EntityListeners(AuditingEntityListener.class)
//class ReviewHelpfulVoteEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "review_id", nullable = false)
//    private ProductReviewEntity review;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private UserEntity user;
//
//    @Column(nullable = false)
//    private Boolean isHelpful = true;
//
//    @Column(nullable = false)
//    private Instant votedAt;
//}