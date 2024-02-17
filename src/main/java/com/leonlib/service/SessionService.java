package com.leonlib.service;

import org.springframework.stereotype.Service;

// import com.leonlib.model.session.RedisSession;


@Service
public class SessionService {

    /* 
    private RedisSessionRepository sessionRepository;

    public SessionService(RedisSessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void addAttribute(String sessionId, String key, Object value) {
        RedisSession session = sessionRepository.findById(sessionId);
        if (session != null) {
            session.getAttributes().put(key, value);
            sessionRepository.save(session);
        }
    }

    public Object getAttribute(String sessionId, String key) {
        RedisSession session = sessionRepository.findById(sessionId);
        return session != null ? session.getAttributes().get(key) : null;
    }
    */

    // Métodos adicionales para manejar sesiones
}