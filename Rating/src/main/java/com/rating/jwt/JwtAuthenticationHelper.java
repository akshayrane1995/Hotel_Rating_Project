package com.rating.jwt;

import java.util.Date;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtAuthenticationHelper {

	private String secret = "thisprojectisforhotelratingusersapplicationwithprovidingsecurityusingjwtandrolesandauthrityisalsoused";

	public String getUsernameFromToken(String token) {

		Claims claims = getClaimsFromToken(token);
		return claims.getSubject();

	}

	public Claims getClaimsFromToken(String token) {

		Claims claims = Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token).getBody();
		return claims;
	}

	public Boolean isTokenExpired(String token) {
		Claims claims = getClaimsFromToken(token);
		Date expDate = claims.getExpiration();
		return expDate.before(new Date());
	}
}