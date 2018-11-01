package com.axonactive.common.ws.rs.client;

import java.util.Objects;

import javax.net.ssl.SSLContext;
import javax.ws.rs.core.Configuration;

import org.jboss.resteasy.client.jaxrs.BasicAuthentication;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

/**
 * an utility class for creating rest clients.
 * @author nvmuon
 *
 */
public class RestClient {
	
	private RestClient() {}

	/**
	 * Create an install of {@link RestClientBuilder} so that it's used for creating rest client objects
	 * @param baseUri base resource uri (e.g. https://axonactive.com/candidate/api). the field is mandatory
	 * @return {@link RestClientBuilder}
	 */
	public static RestClientBuilder createBuilder(String baseUri) {
		return new RestClientBuilder(baseUri);
	}

	/**
	 * a builder for creating rest client objects
	 * @author nvmuon
	 *
	 */
	public static class RestClientBuilder {

		/**
		 * base resource uri (e.g. https://axonactive.com/candidate/api). the field is mandatory
		 */
		private String baseUri;

		/**
		 * SSL context that will be used when creating secured transport connections to server endpoints from web targets
		 */
		private SSLContext sslContext;

		/**
		 * Set the internal configuration state to an externally provided configuration state.
		 */
		private Configuration config;
		
		/**
		 * Contains basic authentication (username and password) for the rest calling
		 */
		private BasicAuthentication basicAuthentication;

		/**
		 * 
		 * @param baseUri base resource uri (e.g. https://axonactive.com/candidate/api). the field is mandatory
		 */
		public RestClientBuilder(String baseUri) {
			this.baseUri = baseUri;
		}

		/**
		 * Set the SSL context that will be used when creating secured transport connections to server endpoints from web targets
		 * created by the client instance that is using this SSL context. 
		 * The SSL context is expected to have all the security infrastructure initialized, including the key and trust managers.
		 * @param sslContext secure socket protocol implementation which acts as a factory for secure socket factories
		 * @return {@link RestClientBuilder}
		 */
		public RestClientBuilder ssl(SSLContext sslContext) {
			this.sslContext = sslContext;
			return this;
		}

		/**
		 * Set the internal configuration state to an externally provided configuration state.
		 * @param config Set the internal configuration state to an externally provided configuration state.
		 * @return {@link RestClientBuilder}
		 */
		public RestClientBuilder withConfig(Configuration config) {
			this.config = config;
			return this;
		}
		
		/**
		 * Apply basic authentication for the rest calling
		 * @param username
		 * @param password
		 * @return {@link RestClientBuilder}
		 */
		public RestClientBuilder withBasicAuth(String username, String password) {
			this.basicAuthentication = new BasicAuthentication(username, password);
			return this;
		}

		/**
		 * Build a proxy object of given rest client interface
		 * @param clientProxyInterface rest client interface
		 * @return the proxy object
		 */
		public <T> T build(Class<T> clientProxyInterface) {
			ResteasyClientBuilder clientBuilder = new ResteasyClientBuilder();
			clientBuilder.connectionPoolSize(200);
			clientBuilder.maxPooledPerRoute(20);
			
			if(Objects.nonNull(sslContext)) {
				clientBuilder.sslContext(sslContext);
			}

			if (Objects.nonNull(config)) {
				clientBuilder.withConfig(config);
			}
			
			if (Objects.nonNull(this.basicAuthentication)) {
				clientBuilder.register(this.basicAuthentication);
			}

			ResteasyWebTarget target = clientBuilder.build().target(this.baseUri);
			return target.proxy(clientProxyInterface);
		}
	}


}
