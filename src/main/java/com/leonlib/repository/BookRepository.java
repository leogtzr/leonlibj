package com.leonlib.repository;

import com.leonlib.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b ORDER BY b.title ASC")
    Page<Book> findAllOrderedByTitle(Pageable pageable);
}
