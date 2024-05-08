package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.user.entity.User;
import com.example.demo.repository.user.UserRepository;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/cadastrar")
	public ResponseEntity login(@RequestBody AuthDTO registerDTO) {
		String encryptedPass = new BCryptPasswordEncoder().encode(registerDTO.password());
		User newUser = new User(registerDTO.username(), encryptedPass, "ADMIN");

		this.userRepository.save(newUser);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/auth")
	public ResponseEntity auth(@RequestBody AuthDTO authDTO) throws AuthenticationException, UsernameNotFoundException {

		var usernamePassword = new UsernamePasswordAuthenticationToken(authDTO.username(), authDTO.password());

		var auth = authenticationManager.authenticate(usernamePassword);
		System.err.println("Tstet");
		List<GrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority("ADMIN"));
		var authToken = new UsernamePasswordAuthenticationToken("usuario", null, list);
		SecurityContextHolder.getContext().setAuthentication(authToken);

		return ResponseEntity.ok().build();
	}

	@GetMapping("/logado")
	public String logado() {
		return "Logado";
	}

	@GetMapping("/teste")
	public String teste() {
		return "teste";
	}
}
