package com.event.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    // used to authenticate users
    private final AuthenticationManager authenticationManager;

    public UserAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // whenever user tries to log in
    // take information from the request and pass it to UsernamePasswordAuthenticationToken
    // then call authenticationManager to authenticate the user
    // if I want to pass credentials as a JSON I can use ObjectMapper on request object
    // the filter is added to http security configuration

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    // this is called after login is successful
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {
        // User from UserDetails
        User user = (User) authentication.getPrincipal();

        // from com.auth0.jwt.algorithms, TODO secret key should be stored encrypted outside of the app and decrypted here!
        Algorithm algorithm = Algorithm.HMAC256("6759a7dc91c84a06ba66665d818bf2a107cb1fafd0da45e1f".getBytes());

        // Create access and refresh token
        String accessToken = JWT.create() // from com.auth0.jwt
            .withSubject(user.getUsername())
            .withIssuedAt(new Date(System.currentTimeMillis()))
            .withExpiresAt(new Date(System.currentTimeMillis() + 5*60*1000))
            .withIssuer(request.getRequestURL().toString()) // URL of the app, but can be any string, for example the company name
            .withClaim("roles", user.getAuthorities()
                .stream()
                .map(grantedAuthority -> "ROLE_" + grantedAuthority.getAuthority())
                .collect(Collectors.toList()))
            .sign(algorithm);

        String refreshToken = JWT.create()
            .withSubject(user.getUsername())
            .withIssuedAt(new Date(System.currentTimeMillis()))
            .withExpiresAt(new Date(System.currentTimeMillis() + 24*60*60*1000))
            .withIssuer(request.getRequestURL().toString())
            .sign(algorithm);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("username", user.getUsername());

        List<String> roles = user.getAuthorities()
            .stream()
            .map(grantedAuthority -> "ROLE_" + grantedAuthority.getAuthority())
            .collect(Collectors.toList());

        tokens.put("roles", String.valueOf(roles));
        response.setContentType(APPLICATION_JSON_VALUE);

        response.setHeader("Access-Control-Allow-Headers",
            "Date, Content-Type, Accept, X-Requested-With, Authorization, From, X-Auth-Token, Request-Id");
        response.setHeader("Access-Control-Expose-Headers", "Set-Cookie");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Set-Cookie",String.format("refreshToken=%s;", refreshToken)  + " Max-Age=86400; Secure; HttpOnly; SameSite=None");
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", failed.getMessage());
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(403);
        new ObjectMapper().writeValue(response.getOutputStream(), errors);
    }
}

