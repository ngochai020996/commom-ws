package com.axonactive.common.ws.rs.auth;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

public class TokenAuthenticationHelper {
	
	private static final String SECRET_KEY = "Aavn123!@#"; //TODO move to configuration file
	
	/** Expired time in minute */
	private static final int TIME_TO_LIVE = 30; //TODO move to configuration file

	private TokenAuthenticationHelper() {}
	
	public static String issueToken(String username) 
			throws UnsupportedEncodingException {
		
		Algorithm algorithm = Algorithm.HMAC512(SECRET_KEY);
		
		return JWT.create()
				.withIssuer("Axon Active Vietnam")
				.withClaim("username", username)
				.withExpiresAt(getExpiredTime())
				.sign(algorithm);
		
	}
	
	public static void validateToken(String token) 
			throws UnsupportedEncodingException {
		
		Algorithm algorithm = Algorithm.HMAC512(SECRET_KEY);
		JWTVerifier verifier = JWT.require(algorithm).build();
		verifier.verify(token);
		
	}
	
	private static Date getExpiredTime() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, TIME_TO_LIVE);
		return cal.getTime();
	}
}
