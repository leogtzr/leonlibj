package com.leonlib.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.leonlib.repository.BookRepository;
import com.leonlib.utils.ModelAttributesHelper;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;

@Controller
public class BooksByAuthorController {

    private static final Logger logger = LoggerFactory.getLogger(BooksByAuthorController.class);

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books_by_author")
    public ModelAndView booksByAuthor(final HttpServletRequest request) {
        final ModelAndView view = new ModelAndView("books_by_author");
        final HttpSession session = request.getSession();

        final List<String> authors = bookRepository.findAllDistinctAuthors();
        view.addObject("authors", authors);

        ModelAttributesHelper.setCommonViewAttributes(view, bookRepository.count());
        ModelAttributesHelper.setLoggedInAttributesInModelAndView(view, session);

        return view;
    }
    
}
