package com.example.demo.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.banco.entity.Banco;

public interface BancoRepository extends JpaRepository<Banco, Long>{

}
