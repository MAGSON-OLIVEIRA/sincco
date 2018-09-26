package com.sincco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sincco.model.Tipologia;
import com.sincco.repository.helper.tipologia.TipologiasQueries;

@Repository
public interface Tipologias extends JpaRepository<Tipologia, Long>, TipologiasQueries {
	public List<Tipologia> findByDescricaoStartingWithIgnoreCase(String nome);
}
