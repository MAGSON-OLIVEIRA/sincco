package com.sincco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sincco.model.Pedido;
import com.sincco.repository.Pedidos;

@Service
public class CadastroPedidoService {
	@Autowired
	private Pedidos pedidos;
	
	@Transactional
	public void salvar(Pedido pedido){
		pedidos.save(pedido);
	}
	
	@Transactional
	public List<Pedido> lista(){
		return pedidos.findAll();
	}
	
	@Transactional
	public void deletar(Pedido pedido){
		pedidos.delete(pedido);
	}
	
	public Pedido pesquisaPorId(Long id){
		return pedidos.findOne(id);
	}
	
}
