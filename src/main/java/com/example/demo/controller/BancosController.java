package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.banco.repository.BancoRepository;

@RestController
@EnableWebSecurity
public class BancosController {
	private final BancoRepository bancoRepository;
	
	public BancosController(BancoRepository bancoRepository) {
		this.bancoRepository = bancoRepository;
	}
	
	@GetMapping("/bancos")
	@PreAuthorize("hasRole('BANCO_LST'")
	public ResponseEntity<List<BancoDTO>> getBancos(){
		List<BancoDTO> listBanco = bancoRepository.findAll().stream().map(item -> new BancoDTO(item.getNome())).collect(Collectors.toList());
		
		
		return ResponseEntity.ok(listBanco);
	}

}
