package com.leonlib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import com.leonlib.model.BookLikes;

@Repository
public interface BookLikeRepository extends JpaRepository<BookLikes, Integer> {
    @Query("SELECT COUNT(*) FROM BookLikes WHERE bookId = :bookId")
    int countByBookId(Integer bookId);

    @Query(value = "SELECT EXISTS(SELECT 1 FROM book_likes WHERE book_id = :bookId AND user_id = :userId)", nativeQuery = true)
    boolean likedBy(Integer bookId, String userId);

    @Modifying
    @Transactional
    default void likeByUser(Integer bookId, String userId) {
        save(new BookLikes(bookId, userId));
    }

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM book_likes WHERE book_id = :bookId AND user_id = :userId", nativeQuery = true)
    void unlikeByUser(Integer bookId, String userId);

    boolean existsByBookIdAndUserId(Integer bookId, String userId);
}