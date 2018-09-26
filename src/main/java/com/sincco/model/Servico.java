package com.sincco.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "servico")
public class Servico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "id_tipologia")
	private Tipologia tipologia;
	
	@ManyToOne
	@JoinColumn(name = "id_cep_servico")
	private CepServico cepServico;

	private LocalDate dataInicio;
	private LocalDate dataFim;
	private String unidadeConsumidora;
	private String observacao;
	private Boolean ativo = new Boolean(false);

	@OneToMany(mappedBy="servico", cascade = CascadeType.ALL)
	private List<Itens> listaItens = new ArrayList<>();
	
	
	
	@Transient
	private String uuid;
	
	@Transient
	private BigDecimal totalMedida = new BigDecimal(0);
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Tipologia getTipologia() {
		return tipologia;
	}

	public void setTipologia(Tipologia tipologia) {
		this.tipologia = tipologia;
	}



	public CepServico getCepServico() {
		return cepServico;
	}

	public void setCepServico(CepServico cepServico) {
		this.cepServico = cepServico;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public String getUnidadeConsumidora() {
		return unidadeConsumidora;
	}

	public void setUnidadeConsumidora(String unidadeConsumidora) {
		this.unidadeConsumidora = unidadeConsumidora;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<Itens> getListaItens() {
		return listaItens;
	}

	public void setListaItens(List<Itens> listaItens) {
		this.listaItens = listaItens;
	}
	
	
	public boolean isNova(){
		return id == null;
	}
	
	
	public void adicionarItens(List<Itens> listaItens){
		this.listaItens = listaItens;
		this.listaItens.forEach(i -> i.setServico(this));
	}
	
    public void getServicoTotalMedida(){
    	BigDecimal totalMediaMoradores = totalMedidaMoradores();
    	BigDecimal totalMediaDiaria = totalMedidaDiaria();
    	if(getListaItens().size() > 0){
          this.totalMedida = totalMediaDiaria.divide(totalMediaMoradores, MathContext.DECIMAL32);  
    	} 
    }

	private BigDecimal totalMedidaMoradores() {
		BigDecimal  totalMediaMoradores = getListaItens().stream()
    			.map(Itens::getQuantidadeMoradores)
    			.reduce(BigDecimal::add)
    			.orElse(BigDecimal.ZERO);
		return totalMediaMoradores;
	}

	private BigDecimal totalMedidaDiaria() {
		BigDecimal  totalMediaDiaria  = getListaItens().stream()
    			.map(Itens::getMedidaDiaria)
    			.reduce(BigDecimal::add)
    			.orElse(BigDecimal.ZERO);
		return totalMediaDiaria;
	}
	
	
	

	public BigDecimal getTotalMedida() {
		return totalMedida;
	}

	public void setTotalMedida(BigDecimal totalMedida) {
		this.totalMedida = totalMedida;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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
		Servico other = (Servico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

}
