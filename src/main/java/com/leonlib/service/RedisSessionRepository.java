package com.leonlib.service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

// import org.springframework.session.data.redis.RedisSession;
import org.springframework.session.Session;  // Use this import instead


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.session.SessionRepository;

// @Service
public class RedisSessionRepository {}
// implements SessionRepository<Session> {

    /* 
    private RedisTemplate<String, Session> redisTemplate;

    public RedisSessionRepository(RedisTemplate<String, Session> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    

    @Override
    public Session createSession() {
        return session;
    }

    @Override
    public void save(RedisSession session) {
        String sessionId = session.getSessionId();
        final ValueOperations<String, RedisSession> ops = redisTemplate.opsForValue();
        ops.set(sessionId, session);
    }

    @Override
public RedisSession findById(String sessionId) {
    ValueOperations<String, RedisSession> ops = redisTemplate.opsForValue();
    return ops.get(sessionId);
}

    @Override
    public void deleteById(String sessionId) {
        redisTemplate.delete(sessionId);
    }

    // Implementa los métodos de SessionRepository para CRUD de sesiones Redis
}
*/