package com.sincco.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="leitura")
public class Leitura implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name="descricao")
	@Size(min= 1, max = 50, message = "Tamanho entre 1 a 50")
	private String descricao;
	
	
	@Column(name="sigla")
	@Size(min= 1, max = 50, message = "Tamanho entre 1 a 50")
	private String sigla;
	
	//@Column(name="valor")
	//@NotBlank(message = "Valor é obrigatorio")
	//private String valor;
	
	//@NotNull(message = "Valor é obrigatório")
	//@DecimalMin(value = "0.50", message = "O valor do deve ser maior que R$0,50")
	//@DecimalMax(value = "9999999.99", message = "O valor do deve ser menor que R$9.999.999,99")
	//private BigDecimal valor;

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
		Leitura other = (Leitura) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}






}
