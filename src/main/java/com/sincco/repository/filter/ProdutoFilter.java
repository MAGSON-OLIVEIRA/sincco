package com.sincco.repository.filter;

public class ProdutoFilter {
	
	private Long id;
	private String descricao;
	private String siglaProduto;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getSiglaProduto() {
		return siglaProduto;
	}
	public void setSiglaProduto(String siglaProduto) {
		this.siglaProduto = siglaProduto;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	

}
