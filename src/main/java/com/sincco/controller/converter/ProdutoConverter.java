package com.sincco.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.sincco.model.Produto;

public class ProdutoConverter implements Converter<String, Produto> {

	@Override
	public Produto convert(String codigo) {
		if(!StringUtils.isEmpty(codigo)){
			Produto produto = new Produto();
			produto.setId(Long.valueOf(codigo));
			return produto;
		}
	return null;
	}
	

}
