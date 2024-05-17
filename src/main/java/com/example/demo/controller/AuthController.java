package com.example.demo.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	
	@GetMapping("/token")
	public ResponseEntity<String> token(@RequestBody AuthDTO authDTO) {

		HttpHeaders header = new HttpHeaders();
		RestTemplate rt = new RestTemplate();
		header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("client_id", authDTO.clientId());
		formData.add("username", authDTO.username());
		formData.add("password", authDTO.password());
		formData.add("grant_type", authDTO.grantType());
		formData.add("client_secret", authDTO.clientSecret());
		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(formData,header);
		
		ResponseEntity<String> result = rt.postForEntity("http://localhost:8080/realms/jonathanAssumpcao/protocol/openid-connect/token", entity, String.class);
		return result;
	}
}
