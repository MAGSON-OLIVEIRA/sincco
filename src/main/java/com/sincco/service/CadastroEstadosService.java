package com.sincco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sincco.model.Estado;
import com.sincco.model.Leitura;
import com.sincco.repository.Estados;

@Service
public class CadastroEstadosService {
	@Autowired
	private Estados estados;
	

	
	@Transactional
	public List<Estado> lista(){
		return estados.findAll();
	}

	
	public Estado pesquisaPorId(Long id){
		return estados.findOne(id);
	}
	
}
