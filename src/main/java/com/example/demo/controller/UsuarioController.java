package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.user.entity.Usuario;
import com.example.demo.domain.user.service.CadastrarUsuarioService;
import com.example.demo.user.repository.PaiRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private final ModelMapper modelMapper;
	private final CadastrarUsuarioService cadastrarUsuarioServiceImpl;
	private final PaiRepository paiRepository;

	@Autowired
	public UsuarioController(ModelMapper modelMapper, CadastrarUsuarioService cadastrarUsuarioServiceImpl, PaiRepository paiRepository) {
		this.modelMapper = modelMapper;
		this.cadastrarUsuarioServiceImpl = cadastrarUsuarioServiceImpl;
		this.paiRepository = paiRepository;
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> cadastrar(@RequestBody @Validated CadastroUsuarioDTO cadastroDTO) {
		Usuario newUser = modelMapper.map(cadastroDTO, Usuario.class);
		return ResponseEntity.ok().body(cadastrarUsuarioServiceImpl.cadastrar(newUser));
	}

	@GetMapping("/logado")
	@PreAuthorize("hasAnyAuthority('USER')")
	public String logado() {
		return "Logado";
	}
	
	@GetMapping("/pais")
	public ResponseEntity<List<PaiDTO>> getPais(){
		List<PaiDTO> listaPai = paiRepository.findAll().stream().map(item -> new PaiDTO(item.getId(), item.getNome())).collect(Collectors.toList());
		return ResponseEntity.ok(listaPai);
	}

}
