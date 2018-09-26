package com.sincco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sincco.model.Moradia;
import com.sincco.repository.Moradias;

@Service
public class CadastroMoradiaService {
	@Autowired
	private Moradias moradias;
	
	@Transactional
	public void salvar(Moradia moradia){
		moradias.save(moradia);
	}
	 
	@Transactional
	public List<Moradia> lista(){
		return moradias.findAll();
	}

	
	public Moradia pesquisaPorId(Long id){
		return moradias.findOne(id);
	}
	
}
