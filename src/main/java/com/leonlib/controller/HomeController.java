package com.leonlib.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.leonlib.repository.BookRepository;
import com.leonlib.service.AuthService;

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

    @GetMapping("/")
    String home(final Model model, final HttpServletRequest request) throws SQLException {
        logger.info("debug:x I am here after login");
        final HttpSession session = request.getSession();

        final Object sub = session.getAttribute("sub");
        boolean loggedIn = false;
        if (sub == null) {
            logger.info("debug:x User might not be logged in");
            model.addAttribute("loggedIn", false);
        } else {
            logger.info("debug:x User IS logged in");
            final String subValue = (String) sub;

            logger.info(String.format("debug:x sub=(%s)", subValue));
            model.addAttribute("loggedIn", true);
        }

        model.addAttribute("year", LocalDate.now().getYear());
        model.addAttribute("booksCount", bookRepository.count());

        return "index";
    }

}
