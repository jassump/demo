package com.example.demo.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.domain.user.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomBasicAuthFilter extends OncePerRequestFilter {

	private static final int BASIC_LENGTH = 6;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		var headerAuthorization = request.getHeader("Authorization");
		if (headerAuthorization == null || !headerAuthorization.startsWith("Basic ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		var basicToken = headerAuthorization.substring(BASIC_LENGTH);
		byte[] basicTokenDecoded = Base64.getDecoder().decode(basicToken);

		String basicTokenValue = new String(basicTokenDecoded);
		String[] basicAuthsSplit = basicTokenValue.split(":");
		
		List<GrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority("ADMIN"));
		var authToken = new UsernamePasswordAuthenticationToken(basicAuthsSplit[0], null, list);
		SecurityContextHolder.getContext().setAuthentication(authToken);

		filterChain.doFilter(request, response);
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request){
		return "/auth".equals(request.getServletPath());
	}

}
