package com.yh.jwt.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yh.jwt.model.User;
import com.yh.jwt.repository.UserRepository;

import lombok.RequiredArgsConstructor;


// http://localhost:8000/login => formLogin().disable()해서 여기서 동작을 안한다.
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("PrincipalDetailsService의 loadUserByUsername()");
		User userEntity = userRepository.findByUsername(username);
		System.out.println("userEntity : "+userEntity);
		return new PrincipalDetails(userEntity);
	}
	
}
