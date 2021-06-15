package com.store.security.filters;

import com.store.security.UserDetailsServiceImpl;
import com.store.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@Order(1)
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("before Filter");
        final String authorizationHeader = request.getHeader("Authorization");
        System.out.println("authorizationHeader: " + authorizationHeader);
        String username = null;
        String jwt = null;
        String uri = request.getRequestURI();
        System.out.println(uri);
        if (uri.contains("login")) {
            System.out.println("before if");
            chain.doFilter(request, response);
            System.out.println("After if");

        } else {

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                System.out.println("authing");
                jwt = authorizationHeader.substring(7);
                username = jwtUtil.extractUsername(jwt);
                System.out.println("username "+username);
            }


            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                if (jwtUtil.validateToken(jwt, userDetails)) {
                    System.out.println("userDetails: " + userDetails.getUsername() +" "+ userDetails.getPassword());
                    System.out.println("valid jwt");
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    System.out.println("auth done");
                }
            }
            chain.doFilter(request, response);
        }
        System.out.println("After Filter");
    }
}
