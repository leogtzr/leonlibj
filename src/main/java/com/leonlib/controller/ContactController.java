package com.leonlib.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.leonlib.config.AppConfig;
import com.leonlib.repository.BookRepository;

import java.sql.SQLException;
import java.time.LocalDate;

@Controller
public class ContactController {
    
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AppConfig appConfig;

    @GetMapping("/contact")
    String contact(final Model model) throws SQLException {
        //logger.info(String.format("debug:x the main user is: (%s)", appConfig.getMainAppUser()));

        model.addAttribute("year", LocalDate.now().getYear());
        model.addAttribute("booksCount", bookRepository.count());

        return "contact";
    }
}
