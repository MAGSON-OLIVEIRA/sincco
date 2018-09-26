package com.sincco.repository.helper.usuario;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sincco.model.Usuario;
import com.sincco.repository.filter.UsuarioFilter;

public interface UsuariosQueries {
	public Page<Usuario> filtar(UsuarioFilter filtro, Pageable pageable);
	
	public List<String> permissoes(Usuario usuario);

}
