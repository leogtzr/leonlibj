package com.leonlib.repository;

import com.leonlib.model.WishListBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishlistRepository extends JpaRepository<WishListBook, Long> {
    @Query("SELECT b FROM WishListBook b ORDER BY b.title ASC")
    List<WishListBook> findAllOrderedByTitle();
}
