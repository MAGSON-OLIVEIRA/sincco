package com.sincco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sincco.model.Servico;
import com.sincco.repository.helper.servico.ServicosQueries;

@Repository
public interface Servicos extends JpaRepository<Servico, Long>, ServicosQueries {
}
