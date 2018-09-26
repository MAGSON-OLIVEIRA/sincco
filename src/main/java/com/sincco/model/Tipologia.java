package com.sincco.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table
public class Tipologia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Descrição é obrigatório")
	private String descricao;
	
	@NotBlank(message = "Sigla é obrigatorio")
	private String sigla;
	
	@NotNull(message = "O tipo de Moradia é Obrigatória")
	@ManyToOne
	@JoinColumn(name="id_moradia")
	private Moradia moradia;
	
	@NotNull(message = "O tipo de leitura é Obrigatória")
	@ManyToOne
	@JoinColumn(name="id_leitura")
	private Leitura leitura;
	
	
	private Date datainclusao;

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getSigla() {
		return sigla;
	}


	public void setSigla(String sigla) {
		this.sigla = sigla;
	}


	public Moradia getMoradia() {
		return moradia;
	}


	public void setMoradia(Moradia moradia) {
		this.moradia = moradia;
	}


	public Leitura getLeitura() {
		return leitura;
	}


	public void setLeitura(Leitura leitura) {
		this.leitura = leitura;
	}


	public Date getDatainclusao() {
		return datainclusao;
	}


	public void setDatainclusao(Date datainclusao) {
		this.datainclusao = datainclusao;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tipologia other = (Tipologia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



}
