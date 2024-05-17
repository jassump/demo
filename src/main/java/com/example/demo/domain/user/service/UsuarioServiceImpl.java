package com.example.demo.domain.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.user.entity.Usuario;
import com.example.demo.user.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository userRepository;
	
	@Autowired
	public UsuarioServiceImpl(UsuarioRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public Usuario loadUserByEmail(String email){
		
		return userRepository.findByEmail(email);
	}
	
}
