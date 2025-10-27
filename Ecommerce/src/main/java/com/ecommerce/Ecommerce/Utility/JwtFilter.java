package com.ecommerce.Ecommerce.Utility;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private JwtService jwtService;

    @Autowired
    public JwtFilter(JwtService jwtService)
    {
        this.jwtService = jwtService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = null;
        String username = null;

        if(request.getCookies() != null)
        {
            for(Cookie cookie : request.getCookies())
            {
                if("jwt".equals(cookie.getName()))
                {
                    token = cookie.getValue();
                    break;
                }
                System.out.println("cookies "+cookie);
            }
        }

        //validate token
        if(token != null && jwtService.validateToken((token)))
        {
            username = jwtService.extractUsername(token);
            request.setAttribute("username", username);
        }
        else if(!request.getRequestURI().contains("/login") && !request.getRequestURI().contains("/register"))
        {
            response.getWriter().write("Unauthorized or invalid token");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
