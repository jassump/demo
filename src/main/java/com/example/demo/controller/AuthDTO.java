package com.example.demo.controller;

public record AuthDTO(String username, String password, String grantType, String clientId, String clientSecret) {}
