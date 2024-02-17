package com.leonlib.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonlib.repository.BookRepository;

@RestController
@RequestMapping("/api")
public class BooksController {

    @Autowired
    private BookRepository bookRepository;
    
    @GetMapping("/booksCount")
    public long booksCount() {
        return bookRepository.count();
    }

}
