/**
 * 
 */
package com.spcm.resource.base;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.StringTokenizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servico de autenticacao do cliente.<br/>
 * Faz a verificacao do usuario e senha de quem esta chamando.
 * 
 * @since 06/2017
 * @author fuji
 *
 */
public class AuthenticationService {
	
	private static final String TRACE_DESCRIPTION = "AuthenticationService";
	
	private static final Logger logger = LogManager.getLogger(AuthenticationService.class);

	public boolean authenticate(String authCredentials) {
		logger.trace(TRACE_DESCRIPTION + ".authenticate <<<");
		
		String userOriginal = "admin";
		String senhaOriginal = "admin";

		if (null == authCredentials)
			return false;

		final String encodedUserPassword = authCredentials.replaceFirst("Basic" + " ", "");
		String usernameAndPassword = null;

		byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);
		usernameAndPassword = new String(decodedBytes, StandardCharsets.UTF_8);
		
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		
		final String username = tokenizer.nextToken();
		final String pw = tokenizer.nextToken();

		return userOriginal.equals(username) && senhaOriginal.equals(pw);
	}
}
