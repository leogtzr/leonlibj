package com.leonlib.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonlib.model.BookImageInfo;

public interface BookImagesRepository extends JpaRepository<BookImageInfo, Long> {
    Optional<List<BookImageInfo>> findByBookId(Integer bookId);
}