package com.skillshiring.demo.config;

import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;

public class JwtProvider {
    private SecretKey key= Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public String generateToken(Authentication auth) {
        return null;

    }
}
