package com.leonlib.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.leonlib.repository.BookRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;

@Controller
public class BooksByAuthorController {

    private static final Logger logger = LoggerFactory.getLogger(BooksByAuthorController.class);

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books_by_author")
    public ModelAndView booksByAuthor() {
        final ModelAndView view = new ModelAndView("books_by_author");

        final List<String> authors = bookRepository.findAllDistinctAuthors();
        view.addObject("authors", authors);

        return view;
    }
    
}
