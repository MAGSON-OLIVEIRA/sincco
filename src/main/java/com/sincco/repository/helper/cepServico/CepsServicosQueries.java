package com.sincco.repository.helper.cepServico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sincco.model.CepServico;
import com.sincco.repository.filter.CepServicoFilter;



public interface CepsServicosQueries {
	public Page<CepServico> filtrar(CepServicoFilter filtro, Pageable pageable);

}
