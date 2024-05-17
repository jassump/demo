package com.example.demo.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CadastroUsuarioDTO{
		
		@Size(min = 3, max=20, message = "Usuário no formato inválido")
		private String username;
		@Email
		private String email;
		
		@Size(min=6, max=20)
		private String password;
		@Pattern(regexp = "^([0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}|[0-9]{2}\\.?[0-9]{3}\\.?[0-9]{3}\\/?[0-9]{4}\\-?[0-9]{2})$")
		private String cpf;
		private String cep ;
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String Email) {
			this.email = Email;
		}
		
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		public String getCpf() {
			return cpf;
		}
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}
		
		public String getCep() {
			return cep;
		}
		public void setCep(String cep) {
			this.cep = cep;
		}
		
}
