package com.example.demo.security.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.domain.user.entity.Usuario;
import com.example.demo.exception.BusinessException;
import com.example.demo.user.repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomBasicAuthFilter extends OncePerRequestFilter {

	private static final int BASIC_LENGTH = 6;

	private final UsuarioRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public CustomBasicAuthFilter(UsuarioRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, BusinessException {
		String headerAuthorization = request.getHeader("Authorization");
		String basicTokenValue = new String(decodeToken(headerAuthorization));
		String[] basicAuthsSplit = basicTokenValue.split(":");
		Usuario user = userRepository.findByEmail(basicAuthsSplit[0]);
		
		if (headerAuthorization == null || !headerAuthorization.startsWith("Basic ")) {
			filterChain.doFilter(request, response);
			return;
		}

		if (user == null || !passwordEncoder.matches(basicAuthsSplit[1], user.getPassword())) {
			throw new BusinessException("Token inválido");

		}

		autenticar(basicTokenValue);
		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return "/cadastrar".equals(request.getServletPath());
	}
	
	private byte[] decodeToken(String headerAuthorization) {
		String basicToken = headerAuthorization.substring(BASIC_LENGTH);
		return Base64.getDecoder().decode(basicToken);
	}
	
	private void autenticar(String usuario) {
		Authentication authToken = new UsernamePasswordAuthenticationToken(usuario, null, null);
		SecurityContextHolder.getContext().setAuthentication(authToken);
		
	}
}
