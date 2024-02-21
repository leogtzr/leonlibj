package com.leonlib.controller;

import com.leonlib.config.AppConfig;
import com.leonlib.model.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.leonlib.repository.BookImagesRepository;
import com.leonlib.repository.BookRepository;
import com.leonlib.service.BookService;
import com.leonlib.utils.ModelAttributesHelper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.SQLException;

@Controller
public class InfoBookController {

    private static final Logger logger = LoggerFactory.getLogger(InfoBookController.class);
    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private AppConfig appConfig;

    @GetMapping("/book_info")
    public ModelAndView bookInfo(@RequestParam(value = "id", required = false, defaultValue = "0") Long id, final HttpServletRequest request) throws SQLException {
        final long bookCount = bookRepository.count();
        final HttpSession session = request.getSession();

        final Optional<Book> book = bookService.findBookById(id);
        if (!book.isPresent()) {
            final ModelAndView errorView = new ModelAndView("error", HttpStatus.BAD_REQUEST);
            ModelAttributesHelper.setCommonViewAttributes(errorView, bookCount);
            
            return errorView;
        }

        final Book bookToDisplay = book.get();
        final ModelAndView model = new ModelAndView("book_info");
        model.addObject("siteKey", appConfig.getCaptchaSiteKey());
        ModelAttributesHelper.setCommonViewAttributes(model, bookCount);
        ModelAttributesHelper.setLoggedInAttributesInModelAndView(model, session);

        model.addObject("results", List.of(bookToDisplay));

        return model;
    }
}
