package es.vicenteqs.ecommercetest.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import es.vicenteqs.ecommercetest.exception.UserEmailNotFoundException;
import es.vicenteqs.ecommercetest.model.repository.UserRepository;

@Service
@Transactional
public class CredentialsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		return this.userRepository.findByEmail(username).orElseThrow(UserEmailNotFoundException::new);
	}
}