/**
 * 
 */
package com.axonactive.common.ws.rs.auth;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nvmuon
 *
 */
@NoArgsConstructor @AllArgsConstructor
public @Data class Credentials implements Serializable {

	private String username;
	private String password;


}
