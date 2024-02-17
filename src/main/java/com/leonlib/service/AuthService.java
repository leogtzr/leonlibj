package com.leonlib.service;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AuthService {

    @Autowired
    private HttpSession httpSession;

    public void guardarNombreUsuario(final String nombreUsuario) {
        httpSession.setAttribute("nombreUsuario", nombreUsuario);
    }

    public String obtenerNombreUsuario() {
        return (String) httpSession.getAttribute("nombreUsuario");
    }
}
