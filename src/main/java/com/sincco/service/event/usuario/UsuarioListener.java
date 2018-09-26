package com.sincco.service.event.usuario;

import org.springframework.stereotype.Component;

@Component
public class UsuarioListener {
	public void usuarioSalvo(UsuarioSalvoEvent evento){
		System.out.println("Salvo com sucesso: " + evento.getUsuario().getNome());
		// posso fazer alguma outra alteração. 
	}

}
