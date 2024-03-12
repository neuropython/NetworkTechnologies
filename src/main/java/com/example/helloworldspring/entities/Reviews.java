package com.example.helloworldspring.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reviews {
    @Id
    private Long reviewId;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;
    private String review;
    private int rating;

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Long getUserId() {
        return user.getUserId();
    }

    public void setUserId(Long userId) {
        this.user.setUserId(userId);
    }

    public Long getBookId() {
        return book.getBookId();
    }

    public void setBookId(Long bookId) {
        this.book.setBookId(bookId);
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
