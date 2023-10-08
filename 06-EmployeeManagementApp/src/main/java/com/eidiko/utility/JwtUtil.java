package com.eidiko.utility;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import com.eidiko.exception.AccesDeniedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	@Value("${app.secret}")
	private String secret;

	// private static final Key secretkey =
	// Keys.secretKeyFor(SignatureAlgorithm.HS512);

	// 1.Generate Token
	public String generateToken(String subject) {
		return Jwts.builder().setSubject(subject).setIssuer("Samarkanta")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15)))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}

	// 2.Read Claims
	public Claims getClaims(String token) {

		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (AccessDeniedException ae) {
			throw new AccesDeniedException("Access Denied");
		}
	}

	// 3.Read Expy Date
	public Date getExpDate(String token) {
		return getClaims(token).getExpiration();
	}

	// 4.Read Subject/username
	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}

	// 5.Validate Exp Date
	public boolean isTokenExp(String token) {

		Date expDate = getExpDate(token);
		return expDate.before(new Date(System.currentTimeMillis()));
	}

	// 6. Validate user name in token and database , expDate
	public boolean validateToken(String token, String username) {

		String tokenUserName = getUsername(token);
		return (username.equals(tokenUserName) && !isTokenExp(token));
	}

}
