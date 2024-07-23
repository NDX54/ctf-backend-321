package com.csit321.ctfbackend.core.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CsrfHeaderFilter extends OncePerRequestFilter {

    private final CsrfTokenRepository csrfTokenRepository;

    public CsrfHeaderFilter(CsrfTokenRepository csrfTokenRepository) {
        this.csrfTokenRepository = csrfTokenRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CsrfToken csrfToken = csrfTokenRepository.generateToken(request);
        csrfTokenRepository.saveToken(csrfToken, request, response);
        if (csrfToken != null) {
            response.setHeader("X-Csrf-Header", csrfToken.getHeaderName());
            response.setHeader("X-Csrf-Param", csrfToken.getParameterName());
            response.setHeader("X-Csrf-Token", csrfToken.getToken());
            filterChain.doFilter(request, response);
        }
    }
}
