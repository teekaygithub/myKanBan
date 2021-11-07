package com.tkato.myKanBan.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tkato.myKanBan.model.User;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken upatoken = new UsernamePasswordAuthenticationToken(username, password);
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        return authenticationManager.authenticate(upatoken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        User user = (User)authResult.getPrincipal();
        String jwt = JwtUtility.generateToken(user.getUsername(), request.getRequestURI().toString());
        Map<String, String> token = new HashMap<>();
        token.put("access_token", jwt);
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        new ObjectMapper().writeValue(response.getOutputStream(), token);
    }
}
