package com.sincco.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.sincco.model.Leitura;

public class LeituraConverter implements Converter<String, Leitura> {

	@Override
	public Leitura convert(String codigo) {
		if(!StringUtils.isEmpty(codigo)){
			Leitura leitura = new Leitura();
			leitura.setId(Long.valueOf(codigo));
			return leitura;
		}
	return null;
	}
	

}
