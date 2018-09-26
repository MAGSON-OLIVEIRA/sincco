package com.sincco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sincco.model.Estado;

public interface Estados extends JpaRepository<Estado, Long>  {
	
}
