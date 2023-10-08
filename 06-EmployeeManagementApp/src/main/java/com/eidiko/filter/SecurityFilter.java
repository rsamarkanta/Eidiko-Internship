package com.eidiko.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.eidiko.utility.JwtUtil;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil util;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String username = null;
		String token = null;

		// 1.read token from Auth read
		token = request.getHeader("Authorization");

		if (token != null) {

			// do validation
			username = util.getUsername(token);

			// username should not be empty, context-auth must be empty
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails user = userDetailsService.loadUserByUsername(username);

				// validate token
				boolean isValid = util.validateToken(token, user.getUsername());

				if (isValid) {

					// try {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,
							user.getPassword(), user.getAuthorities());

					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					// final object stored in SecurityContext with User Details(un,pwd)
					SecurityContextHolder.getContext().setAuthentication(authToken);

//					} catch (IllegalArgumentException ie) {
//						throw new IllegalArgumentException("wrong input argument !");
//					} catch (ExpiredJwtException ie) {
//						throw new ExpiredJwtException("Token expired ! Try with new One !");
//					} catch (MalformedJwtException ie) {
//						throw new MalformedJwtException("Try to get Token Again !");
//					} catch (Exception ie) {
//						ie.printStackTrace();
//					}

				}
			}

		}

		filterChain.doFilter(request, response);

//		try {
//			filterChain.doFilter(request, response);
//		} catch (io.jsonwebtoken.ExpiredJwtException ie) {
//			throw new ExpiredJwtException("Token expired ! Try with new One !");
//		} catch (MalformedJwtException ie) {
//			throw new MalformedJwtException("Try to get Token Again !");
//		} catch (Exception ie) {
//			ie.printStackTrace();
//		}

	}

}
