package com.sincco.repository.helper.tipologia;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sincco.model.Tipologia;
import com.sincco.repository.filter.TipologiaFilter;

public interface TipologiasQueries {
	public Page<Tipologia> filtrar(TipologiaFilter filtro, Pageable pageable);
}
