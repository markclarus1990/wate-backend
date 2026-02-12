package com.msys.water_station.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.msys.water_station.service.JwtService;
import com.msys.water_station.service.MyUserDetailService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();

        if (path.startsWith("/auth/") || path.startsWith("/error")) {
            filterChain.doFilter(request, response);
            return;
        }
        // =====================================================================================
        String authHeader = request.getHeader("Authorization");
        System.out.println("JWT FILTER → Authorization header: " + authHeader);

        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
            System.out.println("JWT FILTER → Extracted username: " + username);
        } else {
            System.out.println("JWT FILTER → No Bearer token found");
        }

        // 🔐 Only authenticate if context is empty
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = applicationContext
                    .getBean(MyUserDetailService.class)
                    .loadUserByUsername(username);

            boolean valid = jwtService.validateToken(token, userDetails);
            System.out.println("JWT FILTER → Token valid: " + valid);

            if (valid) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("JWT FILTER → SecurityContext populated for user: " + username);
            }
        }

        filterChain.doFilter(request, response);
    }
}
