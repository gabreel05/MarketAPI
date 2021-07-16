package br.com.gabriel.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.gabriel.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${sprint-evaluation-4.jwt.expiration}")
	private long expiration;

	@Value("${sprint-evaluation-4.jwt.secret}")
	private String secret;

	public String generateToken(Authentication authentication) {
		User user = (User) authentication.getPrincipal();

		Date date = new Date();

		Long expirationDate = date.getTime() + expiration;

		Date expiration = new Date(expirationDate);

		return Jwts.builder().setIssuer("Market API").setSubject(user.getId().toString()).setIssuedAt(date)
				.setExpiration(expiration).signWith(SignatureAlgorithm.HS256, secret).compact();

	}

	public boolean isValidToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getUserId(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		
		return Long.parseLong(claims.getSubject());
 	}

}
