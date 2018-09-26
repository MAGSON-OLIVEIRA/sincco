package com.sincco.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.sincco.model.Moradia;

public class MoradiaConverter implements Converter<String, Moradia> {

	@Override
	public Moradia convert(String codigo) {
		if(!StringUtils.isEmpty(codigo)){
			Moradia moradia = new Moradia();
			moradia.setId(Long.valueOf(codigo));
			return moradia;
		}
	return null;
	}
	

}
