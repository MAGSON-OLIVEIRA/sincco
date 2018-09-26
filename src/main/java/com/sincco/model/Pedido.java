package com.sincco.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable{
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id")
		private Long id;
		
		
		@ManyToOne
		@JoinColumn(name="idUsuario")
		private Usuario vendedor;
		
		//@ManyToOne
		//@JoinColumn(name="idCliente")
		//private Cliente cliente;
		

		@Column(name="dtCadastro")
		private LocalDate  dtCadastro;
		

		@Column(name="dtEmissao")
		private LocalDate  dtEmissao;
		
		@Column(name="dtFaturamento")
		private LocalDate dtFaturamento;
		

		@OneToMany(mappedBy = "chavePrimaria.pedido", fetch = FetchType.EAGER, orphanRemoval = true)
		private List<PedidoProduto> itens;
		
		
		@Column(name="valorTotal")
		private BigDecimal valorTotal;

		
		
		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public Usuario getVendedor() {
			return vendedor;
		}


		public void setVendedor(Usuario vendedor) {
			this.vendedor = vendedor;
		}


	


		public LocalDate getDtCadastro() {
			return dtCadastro;
		}


		public void setDtCadastro(LocalDate dtCadastro) {
			this.dtCadastro = dtCadastro;
		}


		public LocalDate getDtEmissao() {
			return dtEmissao;
		}


		public void setDtEmissao(LocalDate dtEmissao) {
			this.dtEmissao = dtEmissao;
		}


		public LocalDate getDtFaturamento() {
			return dtFaturamento;
		}


		public void setDtFaturamento(LocalDate dtFaturamento) {
			this.dtFaturamento = dtFaturamento;
		}


		public List<PedidoProduto> getItens() {
			return itens;
		}


		public void setItens(List<PedidoProduto> itens) {
			this.itens = itens;
		}


		public BigDecimal getValorTotal() {
			return valorTotal;
		}


		public void setValorTotal(BigDecimal valorTotal) {
			this.valorTotal = valorTotal;
		}

		
		
		
}
