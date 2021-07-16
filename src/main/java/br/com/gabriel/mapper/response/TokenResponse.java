package br.com.gabriel.mapper.response;

import lombok.Getter;

@Getter
public class TokenResponse {

	public TokenResponse(String token, String type) {
		this.token = token;
		this.type = type;
	}
	
	private String token;
	private String type;

}
