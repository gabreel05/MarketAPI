package br.com.gabriel.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.gabriel.model.User;
import br.com.gabriel.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public AuthenticationService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);

		if (user.isPresent()) {
			return user.get();
		}

		throw new UsernameNotFoundException("Dados inválidos.");
	}

}
