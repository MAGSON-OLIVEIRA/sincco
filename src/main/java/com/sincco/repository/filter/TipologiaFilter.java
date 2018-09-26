package com.sincco.repository.filter;

public class TipologiaFilter {
	
	private Long id;
	private String descricao;
	private String sigla;
	
	private String descricaoMoradia;
	private String descricaoLeitura;
	
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
	public String getDescricaoMoradia() {
		return descricaoMoradia;
	}
	public void setDescricaoMoradia(String descricaoMoradia) {
		this.descricaoMoradia = descricaoMoradia;
	}
	public String getDescricaoLeitura() {
		return descricaoLeitura;
	}
	public void setDescricaoLeitura(String descricaoLeitura) {
		this.descricaoLeitura = descricaoLeitura;
	}
	
}
