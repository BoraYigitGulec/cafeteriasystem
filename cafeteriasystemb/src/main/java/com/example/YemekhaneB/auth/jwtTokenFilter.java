package com.example.YemekhaneB.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class jwtTokenFilter extends OncePerRequestFilter /*her requestte bir defa uygulanacak */{
    private final TokenService TokenService;
    private final UserDetailsService UserDetailsService;
    jwtTokenFilter(TokenService TokenService,UserDetailsService UserDetailsService){
        this.TokenService = TokenService;
        this.UserDetailsService = UserDetailsService;
    }
    @Override
    protected void doFilterInternal( @NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        /* "bearer 123hab2355(token kısmı)" headerlar bu formatta*/
        final String authHeader = request.getHeader("Authorization");
        String token = null;
        String username= null;
        if (request.getRequestURI().equals("/api/auth/create")
                || request.getRequestURI().equals("/api/auth/login")||request.getRequestURI().contains("/swagger-ui/")
                || request.getRequestURI().contains("/v3/api-docs")) {
            filterChain.doFilter(request, response);
            return;
        }
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
            return;
        }
        token = authHeader.substring(7);
        username = TokenService.getUsernameToken(token);
        if (username != null&& token != null
                && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.UserDetailsService.loadUserByUsername(username);
                if(TokenService.tokenValidate(token,userDetails)){
                    UsernamePasswordAuthenticationToken upassToken=
                            new UsernamePasswordAuthenticationToken(userDetails,null,
                                    userDetails.getAuthorities());

                    upassToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(upassToken);
                }
        }
        filterChain.doFilter(request,response);


    }
}
