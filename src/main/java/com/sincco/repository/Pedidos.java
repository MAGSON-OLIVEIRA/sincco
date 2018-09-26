package com.sincco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sincco.model.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Long>  {

}
