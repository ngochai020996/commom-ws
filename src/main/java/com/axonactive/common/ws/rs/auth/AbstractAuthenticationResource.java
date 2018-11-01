/**
 * 
 */
package com.axonactive.common.ws.rs.auth;

import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author nvmuon
 *
 */
public abstract class AbstractAuthenticationResource implements Serializable {

	@POST
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response authenticate(Credentials credentials) {

		try {

			// Authenticate the user using the credentials provided
			authenticate(credentials.getUsername(), credentials.getPassword());

			// Issue a token for the user
			String token = TokenAuthenticationHelper.issueToken(credentials.getUsername());

			// Return the token on the response
			return Response.ok(token).build();

		} catch (Exception e) {
			return Response.status(Response.Status.FORBIDDEN).build();
		}      
	}

	/**
	 * Authenticate against a database, LDAP, file or whatever
	 * @param username
	 * @param password
	 * @throws Exception  if the credentials are invalid
	 */
	protected abstract void authenticate(String username, String password) throws Exception;

	
}
