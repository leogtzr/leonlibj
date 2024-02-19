package com.leonlib.repository;

import com.leonlib.model.Book;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b ORDER BY b.title ASC")
    Page<Book> findAllOrderedByTitle(Pageable pageable);

    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE %:title% ORDER BY b.title")
    List<Book> findByTitleContaining(@Param("title") String title);

    @Query("SELECT b FROM Book b WHERE LOWER(b.author) LIKE %:author% ORDER BY b.title")
    List<Book> findByAuthorContaining(@Param("author") String author);
}
