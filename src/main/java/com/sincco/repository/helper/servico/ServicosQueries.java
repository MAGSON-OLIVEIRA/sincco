package com.sincco.repository.helper.servico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sincco.model.Servico;
import com.sincco.model.Usuario;
import com.sincco.repository.filter.ServicoFilter;
import com.sincco.security.UsuarioSistema;

public interface ServicosQueries {
	public Page<Servico> filtrar(ServicoFilter filtro, Pageable pageable, UsuarioSistema usuario);

}
