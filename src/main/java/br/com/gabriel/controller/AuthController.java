package br.com.gabriel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gabriel.config.security.TokenService;
import br.com.gabriel.mapper.request.UserRequest;
import br.com.gabriel.mapper.response.TokenResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private AuthenticationManager authManager;
	private TokenService tokenService;
	
	@Autowired
	public AuthController(AuthenticationManager authManager, TokenService tokenService) {
		this.authManager = authManager;
		this.tokenService = tokenService;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<TokenResponse> authenticate(@RequestBody @Valid UserRequest userRequest) {
		UsernamePasswordAuthenticationToken data = userRequest.toData();
		
		try {
			Authentication authentication = authManager.authenticate(data);
			String token = tokenService.generateToken(authentication);
			
			System.out.println(token);
			
			return ResponseEntity.ok().body(new TokenResponse(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
