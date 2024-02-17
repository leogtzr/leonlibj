package com.leonlib.controller;

import com.leonlib.model.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.leonlib.repository.BookImagesRepository;
import com.leonlib.repository.BookRepository;
import com.leonlib.service.BookService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.sql.SQLException;

@Controller
public class InfoBookController {

    private static final Logger logger = LoggerFactory.getLogger(InfoBookController.class);
    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookImagesRepository bookImagesRepository;

    @Autowired
    private BookService bookService;

    @GetMapping("/book_info")
    public ModelAndView bookInfo(@RequestParam(value = "id", required = false, defaultValue = "0") int id, final HttpServletRequest request) throws SQLException {
        logger.info(String.format("debug:x id=%d", id));

        final long bookCount = bookRepository.count();

        final Optional<Book> book = bookService.findBookById(Long.valueOf(id));
        if (book.isEmpty()) {
            logger.info("debug:x Here 1");
            final ModelAndView errorView = new ModelAndView("error", HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
            setCommonViewAttributes(errorView, bookCount);
            
            return errorView;
        }

        final ModelAndView model = new ModelAndView("book_info");
        final Book bookToDisplay = book.get();
        logger.info(String.format("debug:x book=(%s)", bookToDisplay));
        setCommonViewAttributes(model, bookCount);

        logger.info(String.format("debug:x book=(%s), model=(%s)", book, model));

        model.addObject("results", List.of(bookToDisplay));

        return model;
    }

    private void setCommonViewAttributes(final ModelAndView view, final long bookCount) {
        view.addObject("booksCount", bookCount);
        view.addObject("year", LocalDate.now().getYear());
    }

}
