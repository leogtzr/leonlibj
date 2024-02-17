package com.leonlib.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.leonlib.repository.BookRepository;

import java.sql.SQLException;
import java.time.LocalDate;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    String home(final Model model) throws SQLException {
        model.addAttribute("year", LocalDate.now().getYear());
        model.addAttribute("booksCount", bookRepository.count());

        return "index";
    }

}