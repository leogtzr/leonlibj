package com.leonlib.service;

import com.leonlib.model.Book;
import com.leonlib.repository.BookImagesRepository;
import com.leonlib.repository.BookRepository;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    
    @Autowired
    private BookRepository bookRepository;

    public Optional<Book> findBookById(final Long id) {
        return bookRepository.findById(id);
    }
}
