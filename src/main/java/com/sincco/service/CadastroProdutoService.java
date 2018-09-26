package com.sincco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sincco.model.Produto;
import com.sincco.repository.Produtos;

@Service
public class CadastroProdutoService {
	@Autowired
	private Produtos produtos;
	
	@Transactional
	public void salvar(Produto produto){
		produtos.save(produto);
	}
	
	@Transactional
	public List<Produto> lista(){
		return produtos.findAll();
	}
	
	@Transactional
	public void deletar(Produto produto){
		produtos.delete(produto);
	}
	
	public Produto pesquisaPorId(Long id){
		return produtos.findOne(id);
	}
	
}
