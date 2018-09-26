package com.sincco.repository.filter;

import java.time.LocalDate;

public class ServicoFilter {
	
	private Long id;
	private Boolean ativo;
	private String nomeUsuario;
	private String emailUsuario;
	
	private String siglaProduto;
	private String siglaTipologia;
	private String cep;
	
	private LocalDate desde;
	private LocalDate ate;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmailUsuario() {
		return emailUsuario;
	}
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public LocalDate getDesde() {
		return desde;
	}
	public void setDesde(LocalDate desde) {
		this.desde = desde;
	}
	public LocalDate getAte() {
		return ate;
	}
	public void setAte(LocalDate ate) {
		this.ate = ate;
	}
	public String getSiglaProduto() {
		return siglaProduto;
	}
	public void setSiglaProduto(String siglaProduto) {
		this.siglaProduto = siglaProduto;
	}
	public String getSiglaTipologia() {
		return siglaTipologia;
	}
	public void setSiglaTipologia(String siglaTipologia) {
		this.siglaTipologia = siglaTipologia;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	

}
