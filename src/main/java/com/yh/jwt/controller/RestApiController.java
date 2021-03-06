package com.yh.jwt.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yh.jwt.model.User;
import com.yh.jwt.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class RestApiController {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("home")
	public String home() {
		return "<h1>home</h1>";
	}
	
	@PostMapping("koken")
	public String koken() {
		return "<h1>koken</h1>";
	}
	
	@PostMapping("join")
	public String join(@RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword())); // 1234ppp -> ABC33333
		user.setRoles("ROLE_USER");
		userRepository.save(user);
		return "회원가입완료";
	}
	
	// user, manager, admin 권한만 접근 가능
	@GetMapping("/api/v1/user")
	public String user() {
		return "user";
	}
	
	// manager, admin 권한만 접근 가능
	@GetMapping("/api/v1/manager")
	public String manager() {
		return "manager";
	}
	
	// admin 권한만 접근 가능
	@GetMapping("/api/v1/admin")
	public String admin() {
		return "admin";
	}
}
