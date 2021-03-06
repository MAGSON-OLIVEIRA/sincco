package com.sincco.model;

import com.sincco.model.validation.group.CnpjGroup;
import com.sincco.model.validation.group.CpfGroup;

public enum TipoPessoa {
	
	FISICA("Física", "CPF", "000.000.000-00", CpfGroup.class )
	{
		@Override
		public String formatar(String cpfCnpj) {
			return cpfCnpj.replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");
		}
	}
	,
	JURIDICA("Jurídica", "CNPJ", "00.000.000/0000-00", CnpjGroup.class)
	{
		@Override
		public String formatar(String cpfCnpj) {
			return cpfCnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})", "$1.$2.$3/$4-");
		}
	}
	;
	
	private String descricao;
	private String documento;
	private String mascara;
	private Class<?> grupo;
	
	
	TipoPessoa(String descricao, String documento, String mascara, Class<?> grupo ){
		this.descricao = descricao;
		this.documento = documento;
		this.mascara = mascara;
		this.grupo = grupo;
	}

	public abstract String formatar(String cpfCnpj);
	

	public String getDescricao() {
		return descricao;
	}


	public String getDocumento() {
		return documento;
	}


	public String getMascara() {
		return mascara;
	}


	public Class<?> getGrupo() {
		return grupo;
	}
	
	
	public static String removerFormatacao(String cpfCnpj){
		return cpfCnpj.replaceAll("\\.|-|/", "");
	}
	
	

}
