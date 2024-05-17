package com.example.demo.domain.user.service;

import com.example.demo.domain.user.entity.Usuario;

public interface UsuarioService {

	Usuario loadUserByEmail(String email);
}
