package com.igor.approve_flow.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.igor.approve_flow.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TokenConfig {

    private final String secret;

    public TokenConfig(@Value("${security.jwt.secret}") String secret) {
        this.secret = secret;
    }

    public String generateToken (User user){

        Algorithm alg = Algorithm.HMAC256(secret);
        return JWT.create()
                .withClaim("userId", user.getId())
                .withSubject(user.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .sign(alg);
    }

    public String validateToken(String token) {
        try {
            Algorithm alg = Algorithm.HMAC256(secret);
            return JWT.require(alg)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }
}
