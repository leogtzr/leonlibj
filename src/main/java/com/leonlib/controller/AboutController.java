package com.leonlib.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.leonlib.repository.BookRepository;
import com.leonlib.utils.ModelAttributesHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.SQLException;
import java.time.LocalDate;


@Controller
public class AboutController {
    
    private static final Logger logger = LoggerFactory.getLogger(AboutController.class);

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/about")
    String about(final HttpServletRequest request, final Model model) throws SQLException {
        final HttpSession session = request.getSession();
        
        model.addAttribute("year", LocalDate.now().getYear());
        model.addAttribute("booksCount", bookRepository.count());

        model.addAttribute("numberOfBooks", ((bookRepository.count() / 100) + 1) * 100);

        ModelAttributesHelper.setLoggedInAttributesInModel(model, session);

        return "about";
    }

}
