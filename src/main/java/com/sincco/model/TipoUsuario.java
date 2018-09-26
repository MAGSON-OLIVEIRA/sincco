package com.sincco.model;

public enum TipoUsuario {
	VENDEDOR("Vendedor"),
	ATENDENTE("Atendente"),
	CLIENTE("Cliente");
	
	private String descricao;
	
	TipoUsuario(String descricao){
		this.descricao = descricao;
	}
	public String getDescricao(){
		return descricao;
	}

}
