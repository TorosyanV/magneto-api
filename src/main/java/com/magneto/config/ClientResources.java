package com.magneto.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

public class ClientResources {

	private OAuth2ProtectedResourceDetails client = new AuthorizationCodeResourceDetails();
	private ResourceServerProperties resource = new ResourceServerProperties();

	public OAuth2ProtectedResourceDetails getClient() {
		System.out.println("getClient");
		return client;
	}

	public ResourceServerProperties getResource() {
		System.out.println("getResource");
		return resource;
	}
}