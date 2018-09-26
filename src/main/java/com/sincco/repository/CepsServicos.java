package com.sincco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sincco.model.CepServico;
import com.sincco.repository.helper.cepServico.CepsServicosQueries;


public interface CepsServicos extends JpaRepository<CepServico, Long>, CepsServicosQueries{
	public List<CepServico> findByCepStartingWithIgnoreCase(String cep);
}
