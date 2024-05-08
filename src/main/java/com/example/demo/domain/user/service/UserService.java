package com.example.demo.domain.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.user.entity.User;
import com.example.demo.repository.user.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User loadUserByEmail(String email) {
		return userRepository.findByEmail(email).get();
	}
	
}
