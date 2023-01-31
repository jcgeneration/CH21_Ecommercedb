package org.generation.ecommercedb.model;
public class Token {
	private final String accessToken;
	public Token(String accessToken) {
		super();
		this.accessToken = accessToken;
	}//constructor
	public String getAccessToken() {
		return accessToken;
	}//getAccessToken
}//class Token
