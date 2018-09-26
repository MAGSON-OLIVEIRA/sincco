package com.sincco.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sincco.model.Servico;

@Component
public class ServicoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Servico.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "produto.id", "", "Selecione um produto.");
		
		ValidationUtils.rejectIfEmpty(errors, "tipologia.id", "", "Selecione uma tipologia na pessquisa rapida.");
		
		ValidationUtils.rejectIfEmpty(errors, "cepServico.id", "", "Selecione uma CEP na pessquisa rapida.");
		
		Servico servico = (Servico) target;
		
		if(StringUtils.isEmpty(servico.getUnidadeConsumidora())){
			errors.rejectValue("unidadeConsumidora", "", "Informe uma unidade de Consumo.");	
		}

		if(servico.getListaItens().isEmpty()){
			errors.reject("", "Adicione pelo menos um item no serviço.");
		}
		
	}

}
