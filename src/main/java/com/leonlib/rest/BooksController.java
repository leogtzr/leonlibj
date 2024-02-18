package com.leonlib.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leonlib.repository.BookLikeRepository;
import com.leonlib.repository.BookRepository;

@RestController
@RequestMapping("/api")
public class BooksController {

    private static final Logger logger = LoggerFactory.getLogger(BooksController.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookLikeRepository bookLikeRepository;
    
    @GetMapping("/booksCount")
    public long booksCount() {
        return bookRepository.count();
    }

    @GetMapping("/likes_count")
    public Map<String, Integer> likesCountByUser(@RequestParam("book_id") final Integer bookId) {
        final int count = bookLikeRepository.countByBookId(bookId);
        return Map.of("count", count);
    }

}
