package com.sefako.makgatho.demo.models;

public class AuthenticationResponse {
	
	private String jwtToken;
	
	public void setJwtToken(String jwtToken){
		this.jwtToken = jwtToken;
	}
	
	public String getJwtToken() {
		return this.jwtToken;
	}
}
