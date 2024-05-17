package com.example.demo.domain.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.user.entity.Usuario;
import com.example.demo.exception.BusinessException;
import com.example.demo.user.repository.UsuarioRepository;

@Service
public class CadastrarUsuarioServiceImpl implements CadastrarUsuarioService{
	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;
	
	public CadastrarUsuarioServiceImpl(UsuarioRepository usuarioRepository,PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public Usuario cadastrar(Usuario usuario)throws BusinessException {
		if(usuarioRepository.existsByEmail(usuario.getEmail())) throw new BusinessException("Email já cadastrado");
		if(usuarioRepository.existsByCpf(usuario.getCpf())) throw new BusinessException("CPF já cadastrado");
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuarioRepository.save(usuario);
		return usuario;
	}

}
