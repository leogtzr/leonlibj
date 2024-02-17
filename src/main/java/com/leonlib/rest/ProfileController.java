package com.leonlib.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class ProfileController {
    
    @GetMapping("/login")
    public String login(final HttpServletRequest request) {
        final HttpSession session = request.getSession();

        // Set attributes:
        // session.setAttribute("userId", 123);
        // session.setAttribute("username", "user123");
        // session.setAttribute("email", "user123@example.com");

        return "OK";
    }

    @GetMapping("/profile")
    public String profile(final HttpServletRequest request) {
        // Retrieve attributes:
        final HttpSession session = request.getSession();

        final Integer userId = (Integer) session.getAttribute("userId");
        final String username = (String) session.getAttribute("username");
        final String email = (String) session.getAttribute("email");
        
        return String.format("User ID: %d, Username: %s, Email: %s", userId, username, email);
    }

}
