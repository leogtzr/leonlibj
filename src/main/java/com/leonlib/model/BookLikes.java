package com.leonlib.model;

// import javax.persistence.*;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book_likes")
public class BookLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Integer likeId;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "user_id")
    private String userId;

    // Constructor, getters, and setters
    public BookLikes() {
        // Default constructor
    }

    public BookLikes(Integer bookId, String userId) {
        this.bookId = bookId;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
    }

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(final Integer likeId) {
        this.likeId = likeId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(final Integer bookId) {
        this.bookId = bookId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }
}
