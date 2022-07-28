package com.event.security.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.event.role.Role;
import com.event.user.User;
import com.event.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin("*")
public class TokenRefreshController {

    private final UserService userService;

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String refreshTokenCookie = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("refreshToken") && !c.getValue().equals("")) {
                    refreshTokenCookie = c.getValue();
                }
            }
        }

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.replace("Bearer ", "");
                if (refreshToken.equals("undefined")) refreshToken = refreshTokenCookie;
                Algorithm algorithm = Algorithm.HMAC256("6759a7dc91c84a06ba66665d818bf2a107cb1fafd0da45e1f".getBytes()); // the secret set in UserAuthenticationFilter
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                User user = userService.getUserByLogin(username); // app user
                String accessToken = JWT.create()
                    .withSubject(user.getFirstName())
                    .withIssuedAt(new Date(System.currentTimeMillis()))
                    .withExpiresAt(new Date(System.currentTimeMillis() + 5*60*1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles", user.getUserRoles()
                        .stream()
                        .map(role -> "ROLE_" + role.getName())
                        .toList())
                    .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("accessToken", accessToken);
                tokens.put("username", user.getLogin());


//                List<String> userRoles = user.getUserRoles().stream().map(Role::getName).toList();
                List<String> userRoles = user.getUserRoles().stream().map(role -> "ROLE_" + role.getName()).toList();


                tokens.put("roles", String.valueOf(userRoles));
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException ("Refresh token is missing");
        }
    }

}
