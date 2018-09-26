package com.sincco.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sincco.model.CepServico;
import com.sincco.model.Itens;
import com.sincco.repository.CepsServicos;

@Service
public class CadastroCepServicoService {
	@Autowired
	private CepsServicos cepsServicos;
	
	@Transactional
	public void salvar(CepServico cepServico){
		cepsServicos.save(cepServico);
	}
	
	@Transactional
	public List<CepServico> lista(){
		return cepsServicos.findAll();
	}
	
	@Transactional
	public void deletar(CepServico cepServico){
		cepsServicos.delete(cepServico);
	}
	
	public CepServico pesquisaPorId(Long id){
		return cepsServicos.findOne(id);
	}

	public List<CepServico> findByCepStartingWithIgnoreCase(String cep) {
		//return cepsServicos.findByCepStartingWithIgnoreCase(cep);
		List<CepServico>  lista = new ArrayList<CepServico>();
		List<CepServico>   itens = cepsServicos.findByCepStartingWithIgnoreCase(cep);
		
		for (CepServico ceps : itens){
			ceps.setEstado(ceps.getCidade().getEstado());
			lista.add(ceps);
		}
		
		return lista;


	}

	
}
