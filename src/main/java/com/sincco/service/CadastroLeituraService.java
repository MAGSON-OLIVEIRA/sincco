package com.sincco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sincco.model.Leitura;
import com.sincco.model.Produto;
import com.sincco.repository.Leituras;

@Service
public class CadastroLeituraService {
	@Autowired
	private Leituras leituras;
	
	@Transactional
	public void salvar(Leitura leitura){
		leituras.save(leitura);
	}
	
	@Transactional
	public List<Leitura> lista(){
		return leituras.findAll();
	}
	
	@Transactional
	public void deletar(Leitura leitura){
		leituras.delete(leitura);
	}
	
	public Leitura pesquisaPorId(Long id){
		return leituras.findOne(id);
	}
	
}
