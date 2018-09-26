package com.sincco.model.validation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.sincco.model.Usuario;

public class UsuarioGroupSequenciaProvider implements DefaultGroupSequenceProvider<Usuario> {

	@Override
	public List<Class<?>> getValidationGroups(Usuario usuario) {
		List<Class<?>> groups = new ArrayList<>();
		groups.add(Usuario.class);
		
		if(isPessoaSelecionada(usuario)){
			groups.add(usuario.getTipoPessoa().getGrupo());
		}
		
		return groups;
	}
	
	
	private boolean isPessoaSelecionada(Usuario usuario){
		return usuario != null && usuario.getTipoPessoa() != null;
	}

}
