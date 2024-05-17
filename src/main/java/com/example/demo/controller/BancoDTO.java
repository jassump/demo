package com.example.demo.controller;

public record BancoDTO(String nome) {
	
	public BancoDTO(String nome) {
		this.nome = nome;
	}
}
