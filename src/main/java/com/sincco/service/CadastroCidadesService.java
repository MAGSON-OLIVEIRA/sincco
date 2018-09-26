package com.sincco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sincco.model.Cidade;
import com.sincco.repository.Cidades;

@Service
public class CadastroCidadesService {
	@Autowired
	private Cidades cidades;
	

	
	@Transactional
	public List<Cidade> lista(){
		return cidades.findAll();
	}

	
	public Cidade pesquisaPorId(Long id){
		return cidades.findOne(id);
	}
	
}
