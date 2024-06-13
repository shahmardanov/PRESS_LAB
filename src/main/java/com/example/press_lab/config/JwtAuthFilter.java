package com.example.press_lab.config;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private static final String AUTH_HEADER = "Authorization";
    public static final String BEARER = "Bearer ";
    private static final String ROLE_CLAIM = "ROLE";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Claims claims = null;
        Authentication authenticationBearer = null;
        if (request.getHeader(AUTH_HEADER) != null) {
            claims = jwtService.tokenParser(removeBearerHeader(request.getHeader(AUTH_HEADER)));
            authenticationBearer = getAuthenticationBearer(claims);
        }
        SecurityContextHolder.getContext().setAuthentication(authenticationBearer);
        filterChain.doFilter(request, response);
    }

    private Authentication getAuthenticationBearer(Claims claims) {
        Map<String, Object> claims1 = (Map<String, Object>) claims.get("claims");
        List<?> roles = (List<?>) claims1.get("ROLE");

//        List<?> roles = claims.get("ROLE", List.class);
        List<GrantedAuthority> authorityList = roles
                .stream()
                .map(a -> new SimpleGrantedAuthority("ROLE_" + a.toString()))
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorityList);
    }



    private String removeBearerHeader(String jwt) {
        if (jwt.startsWith(BEARER)) {
            return jwt.substring(BEARER.length());
        }
        throw new RuntimeException("This is not BEARER token!");
    }

}