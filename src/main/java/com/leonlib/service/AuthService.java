package com.leonlib.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.Claim;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private HttpSession httpSession;

    public void setAuthAttributes(final Map<String, Claim> claims, final HttpSession session) {
        session.setAttribute("sub", claims.get("sub").asString());
        session.setAttribute("email", claims.get("email").asString());
        session.setAttribute("name", claims.get("name").asString());
    }
}
