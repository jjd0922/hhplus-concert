package com.hanghe.common.filter;

import com.hanghe.common.servlet.CachedBodyHttpServletRequest;
import com.hanghe.common.servlet.CachedBodyHttpServletResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException {

        CachedBodyHttpServletRequest wrappedRequest = new CachedBodyHttpServletRequest(request);
        CachedBodyHttpServletResponse wrappedResponse = new CachedBodyHttpServletResponse(response);

        long startTime = System.currentTimeMillis();

        try {
            logRequest(wrappedRequest);
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } finally {
            logResponse(wrappedResponse, startTime);
        }
    }

    private void logRequest(CachedBodyHttpServletRequest request) throws IOException {
        String body = new String(request.getCachedBody(), request.getCharacterEncoding());
        logger.info("Request [{} {}], Body: {}", request.getMethod(), request.getRequestURI(), body);
    }

    private void logResponse(CachedBodyHttpServletResponse response, long startTime) throws IOException {
        long duration = System.currentTimeMillis() - startTime;
        String body = new String(response.getContentAsByteArray(), response.getCharacterEncoding());
        logger.info("Response [{}], Status: {}, Duration: {} ms, Body: {}",
                response.getContentType(), response.getStatus(), duration, body);
    }
}
