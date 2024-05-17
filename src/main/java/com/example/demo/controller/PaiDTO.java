package com.example.demo.controller;

public record PaiDTO(Long id, String nome) {
	
	public PaiDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
}
