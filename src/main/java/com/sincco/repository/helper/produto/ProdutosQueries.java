package com.sincco.repository.helper.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sincco.model.Produto;
import com.sincco.repository.filter.ProdutoFilter;

public interface ProdutosQueries {
	public Page<Produto> filtrar(ProdutoFilter filtro, Pageable pageable);

}
