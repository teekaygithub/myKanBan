package com.tkato.myKanBan.security;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtility {
    
    // TODO: constant for secret key

    public String generateToken(String subject, String issuer) throws JWTCreationException {
        Algorithm algorithm = Algorithm.HMAC512("secret");
        Date expiration = new Date(System.currentTimeMillis() + 10 * 60 * 1000);
        String token = JWT.create()
            .withSubject(subject)
            .withExpiresAt(expiration)
            .withIssuer(issuer)
            .sign(algorithm);
        return token;
    }

    public DecodedJWT verifyToken(String token, String issuer) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC512("secret");
        JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer(issuer)
            .build();
        DecodedJWT decoded = verifier.verify(token);
        return decoded;
    }
}
