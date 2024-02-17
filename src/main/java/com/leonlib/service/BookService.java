package com.leonlib.service;

import com.leonlib.model.Book;
import com.leonlib.model.BookImageInfo;
import com.leonlib.repository.BookImagesRepository;
import com.leonlib.repository.BookRepository;

import java.util.Base64;
import java.util.List;
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

    @Autowired
    private BookImagesRepository bookImagesRepository;

    public Optional<Book> findBookById(final long id) {
        final Optional<Book> bookFromDB = bookRepository.findById(id);

        bookFromDB.ifPresent(book -> {
            logger.info(String.format("debug:x bookFound=(%s)", book));
            bookImagesRepository.findByBookId(book.getId()).ifPresent(bookImages -> {
                logger.info(String.format(String.format("debug:x img found")));
            });
        });

        return bookFromDB;
    }
}
