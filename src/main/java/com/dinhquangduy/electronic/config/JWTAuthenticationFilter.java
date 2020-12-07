package com.dinhquangduy.electronic.config;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
 
public class JWTAuthenticationFilter extends OncePerRequestFilter{
    
    @Autowired
    private JwtTokenProvider jwtTokenUtil;
    
    @Autowired
    private UserDetailsService userDetailsService;
     
    @Override
    public void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse  servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
         
        System.out.println("JWTAuthenticationFilter.doFilter");
        String header = servletRequest.getHeader("Authorization");
        String username = null;
        String authToken = null;

        try {
            
        
        if (header != null && (header.startsWith("Bearer ") || header.startsWith("bearer "))) {

            authToken = header.substring(7);

            try {

                username = jwtTokenUtil.getUsernameFromToken(authToken);

            } catch (IllegalArgumentException  e) {

                logger.error("an error occured during getting username from token", e);
                //throw new IllegalArgumentException("an error occured during getting username from token", e);
               

            } catch (ExpiredJwtException e) {

                logger.warn("the token is expired and not valid anymore", e);
               // throw new ExpiredJwtException(e.getHeader(), e.getClaims(), e.getMessage());
            }
        } else {
            logger.warn("couldn't find bearer string, will ignore the header");
            //throw new IllegalArgumentException("couldn't find bearer string, will ignore the header");
        }
        
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(authToken, userDetails)) {

                String role = "";

                role = userDetails.getAuthorities().size() > 1 ? "ROLE_ADMIN" : "ROLE_VIEWER";

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, Arrays.asList(new SimpleGrantedAuthority(role)));

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(servletRequest));

                logger.info("authenticated user " + username + ", setting security context");

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
        } catch (ServletException e) {
            throw new ServletException("an error occured during getting username from token", e);
        }
         
    }
     
}