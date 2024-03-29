package com.example.helloworldspring.security;

import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JWTTokenFilter extends OncePerRequestFilter {
    private final String key;

    public JWTTokenFilter(String key){
        this.key = key;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            String token = authHeader.split(" ")[1];

            try {
                Claims claims = Jwts.parser().setSigningKey(key)
                        .build().parseClaimsJws(token).getBody();
                String id = (String) claims.get("id");
                String role = (String) claims.get("role");

                Authentication authentication = new UsernamePasswordAuthenticationToken(id,
                        null,
                        List.of(new SimpleGrantedAuthority(role)));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (ExpiredJwtException e) {
                // Handle expired token here
            } catch (SignatureException e) {
                // Handle invalid signature here
            } catch (JwtException e) {
                // Handle other JWT related exceptions here
            }
        } else {
            SecurityContextHolder.getContext().setAuthentication(null);
        }

        filterChain.doFilter(request, response);
    }
}