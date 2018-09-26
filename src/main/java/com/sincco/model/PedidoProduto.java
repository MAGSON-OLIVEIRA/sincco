package com.sincco.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "pedidoProduto")
public class PedidoProduto implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PedidoProdutoPK chavePrimaria;

	@Column(name="quantidade")
	private Integer qantidade;

	public PedidoProdutoPK getChavePrimaria() {
		return chavePrimaria;
	}

	public void setChavePrimaria(PedidoProdutoPK chavePrimaria) {
		this.chavePrimaria = chavePrimaria;
	}

	public Integer getQantidade() {
		return qantidade;
	}

	public void setQantidade(Integer qantidade) {
		this.qantidade = qantidade;
	}

	
	
	
}
