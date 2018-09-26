package com.sincco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sincco.model.Tipologia;
import com.sincco.repository.Tipologias;

@Service
public class CadastroTipologiaService {
	
	@Autowired
	private Tipologias tipologias;
	
	@Transactional
	public void salvar(Tipologia tipologia){
		tipologias.save(tipologia);
	}
	
	@Transactional
	public List<Tipologia> lista(){
		return tipologias.findAll();
	}
	
	@Transactional
	public void deletar(Tipologia tipologia){
		tipologias.delete(tipologia);
	}
	
	public Tipologia pesquisaPorId(Long id){
		return tipologias.findOne(id);
	}
	
	public List<Tipologia> findByDescricaoStartingWithIgnoreCase(String descricao){
		List<Tipologia> tipologiasOptional = tipologias.findByDescricaoStartingWithIgnoreCase(descricao);
		
		if(tipologiasOptional.isEmpty()){
			System.out.println("teste");
		}
		return tipologiasOptional;
		
	}
	
}
