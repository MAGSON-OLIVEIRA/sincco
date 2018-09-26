package com.sincco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sincco.model.Leitura;

public interface Leituras extends JpaRepository<Leitura, Long>  {
	
}
