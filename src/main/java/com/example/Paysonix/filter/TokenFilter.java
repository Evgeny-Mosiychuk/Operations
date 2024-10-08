package com.example.Paysonix.filter;

import com.example.Paysonix.controller.OperationController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class TokenFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(OperationController.class);
    private final String token;

    public TokenFilter(String token) {
        this.token = token;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String headerToken = httpServletRequest.getHeader("Token");

        if (headerToken == null || !headerToken.equals(token)) {
            logger.warn("Unauthorized access attempt with token: {}", headerToken);

            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);

            //Optional
            Map<String, String> responseMessage = Map.of(
                    "status", "error",
                    "message", "Unauthorized access: Invalid or missing token."
            );

            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(responseMessage));
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
