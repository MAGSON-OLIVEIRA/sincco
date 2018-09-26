package com.sincco.service.event.usuario;

import com.sincco.model.Usuario;

public class UsuarioSalvoEvent {

	private Usuario usuario;

	public UsuarioSalvoEvent(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
