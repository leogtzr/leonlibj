package com.leonlib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.leonlib.model.BookLikes;

@Repository
public interface BookLikeRepository extends JpaRepository<BookLikes, Integer> {
    @Query("SELECT COUNT(*) FROM BookLikes WHERE bookId = :bookId")
    int countByBookId(Integer bookId);
}