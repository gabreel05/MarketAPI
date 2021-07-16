package br.com.gabriel.mapper.request;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

	private String username;
	private String password;
	
	public UsernamePasswordAuthenticationToken toData() {
		return new UsernamePasswordAuthenticationToken(username, password);
	}
	
}
