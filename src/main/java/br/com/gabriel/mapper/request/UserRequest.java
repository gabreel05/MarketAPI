package br.com.gabriel.mapper.request;

import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

	@NotBlank
	private String username;
	@NotBlank
	private String password;
	
	public UsernamePasswordAuthenticationToken toData() {
		return new UsernamePasswordAuthenticationToken(username, password);
	}
	
}
