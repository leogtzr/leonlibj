package com.leonlib.controller;

import java.util.Map;

import com.auth0.AuthenticationController;
import com.auth0.IdentityVerificationException;
import com.auth0.Tokens;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.leonlib.config.AppConfig;
import com.leonlib.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationController authenticationController;

    private final AppConfig appConfig;

    @Autowired
    private AuthService authService;

    @Autowired
    public AuthController(final AppConfig appConfig) throws UnsupportedEncodingException {
        this.appConfig = appConfig;

        this.authenticationController = AuthenticationController
            .newBuilder(appConfig.getAuthDomain(), appConfig.getAuthClientId(), appConfig.getAuthClientSecret())
        .build();
    }

    @GetMapping("/logeo")
    public String login(final HttpServletResponse response) {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        final String authorizeUrl = authenticationController
            .buildAuthorizeUrl(request, response, this.appConfig.getAuthCallbackURL())
                .withScope("openid email profile")
                .build();

        return "redirect:" + authorizeUrl;
    }

    @GetMapping("/auth/callback")
    public String callback(@RequestParam(name = "code") final String code, final HttpServletRequest request, final HttpServletResponse response) {
        try {
            final Tokens tokens = authenticationController
                .handle(request, response);
            final DecodedJWT jwt = JWT.decode(tokens.getIdToken());
            
            // Maneja la información del usuario, por ejemplo:
            final String userId = jwt.getSubject();
            final String userEmail = jwt.getClaim("email").asString();

            final HttpSession session = request.getSession();
            final Map<String, Claim> claims = jwt.getClaims();
            authService.setAuthAttributes(claims, session);

            // Hacer algo con la información del usuario, como almacenarla en una sesión, etc.
            logger.info(String.format("debug:x :) yeiii! userId=(%s), email=(%s)", userId, userEmail));

            return "redirect:/";
        } catch (final IdentityVerificationException ex) {
            logger.error("error: ", ex);

            return "redirect:/error";
        }
    }

    
}