package com.tkato.myKanBan.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/api/users/login")) {
            // This filter is not for authentication, pass off to rest of chain
            filterChain.doFilter(request, response);
        } else {
            String authHeader = request.getHeader("Authorization");
            System.out.println(authHeader);
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                System.out.println("Extracting token...");
                // Extract token from header and run through verifier
                String token = authHeader.substring("Bearer ".length());
                DecodedJWT decoded = JwtUtility.verifyToken(token);
                String username = decoded.getSubject();
                
                System.out.println("Verifying token...");
                // Authenticate with spring security
                UsernamePasswordAuthenticationToken upatoken = new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(upatoken);
                System.out.println("Verification complete");
                filterChain.doFilter(request, response);
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }    
}
