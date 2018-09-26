package com.sincco.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "item_servico")
public class Itens implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne 
	@JoinColumn(name="id_servico")
	private Servico servico;
	private BigDecimal medida;
	private BigDecimal quantidadeMoradores;
	private BigDecimal diasMedido;
	private LocalDate dataCompetencia;
	@Transient
	private Integer contador;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}




	public BigDecimal getQuantidadeMoradores() {
		return quantidadeMoradores;
	}

	public void setQuantidadeMoradores(BigDecimal quantidadeMoradores) {
		this.quantidadeMoradores = quantidadeMoradores;
	}

	public BigDecimal getDiasMedido() {
		return diasMedido;
	}

	public void setDiasMedido(BigDecimal diasMedido) {
		this.diasMedido = diasMedido;
	}

	public BigDecimal getMedida() {
		return medida;
	}

	public void setMedida(BigDecimal medida) {
		this.medida = medida;
	}


	
	

    public LocalDate getDataCompetencia() {
		return dataCompetencia;
	}

	public void setDataCompetencia(LocalDate dataCompetencia) {
		this.dataCompetencia = dataCompetencia;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public BigDecimal getMedidaLitro(){
        BigDecimal mil = new BigDecimal(1000);
        return medida.multiply(mil);

  }

    public Integer getContador() {
		return contador;
	}

	public void setContador(Integer contador) {
		this.contador = contador;
	}

public BigDecimal getMedidaDiaria(){
        BigDecimal valorUnitario = getMedidaLitro().divide(diasMedido, MathContext.DECIMAL32);
        return valorUnitario;
        //return getMedidaLitro().divide(new BigDecimal(diasMedido));
  }

  public BigDecimal getMedidaDiariaPorMoradores(){
        return getMedidaDiaria().divide(quantidadeMoradores,MathContext.DECIMAL32 );
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
		Itens other = (Itens) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
