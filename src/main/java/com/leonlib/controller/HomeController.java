package com.leonlib.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.leonlib.config.AppConfig;
import com.leonlib.repository.BookRepository;
import com.leonlib.service.AuthService;
import com.leonlib.utils.ModelAttributesHelper;

import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private AppConfig appConfig;

    @GetMapping("/")
    String home(final Model model, final HttpServletRequest request) throws SQLException {
        final HttpSession session = request.getSession();

        model.addAttribute("year", LocalDate.now().getYear());
        model.addAttribute("booksCount", bookRepository.count());
        model.addAttribute("siteKey", appConfig.getCaptchaSiteKey());

        ModelAttributesHelper.setLoggedInAttributesInModel(model, session);

        return "index";
    }

}
