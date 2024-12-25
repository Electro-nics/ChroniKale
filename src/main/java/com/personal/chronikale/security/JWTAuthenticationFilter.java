package com.personal.chronikale.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	private UserDetailsService detailsService;
	@Autowired
	private JWTTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// gET TOKEN
		
		String requestToken= request.getHeader("Authorization");
		
		String username=null;
		String token=null;
		
		if(requestToken!=null && requestToken.startsWith("Bearer")) {
			
			token = requestToken.substring(7);
			
			try {
			username= this.jwtTokenHelper.getUsernameFromToken(token);
			}
			catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT token");
				// TODO: handle exception
			}
			catch (ExpiredJwtException e) {
				System.out.println("JWT token has expired");
				// TODO: handle exception
			}
			catch (MalformedJwtException e) {
				System.out.println("Invalid JWT");
				// TODO: handle exception
			}
		}else {
			System.out.println("JWT token does not begain with Bearer");
		}
		
		
		// validate the token
		
		if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			UserDetails userDetails= this.detailsService.loadUserByUsername(username);
			
			if(this.jwtTokenHelper.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken passwordAuthenticationToken=
						new UsernamePasswordAuthenticationToken(
								userDetails,
								null,
								userDetails.getAuthorities()
								);
				
				passwordAuthenticationToken
				.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(passwordAuthenticationToken);
				
			}else {
				System.out.println("Invalid Token !!");
			}
		}else {
			System.out.println("Username is null or Context is null");
		}
		
		filterChain.doFilter(request, response);
		
	}

}