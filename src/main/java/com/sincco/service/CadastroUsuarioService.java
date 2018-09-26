package com.sincco.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sincco.model.Usuario;
import com.sincco.repository.Usuarios;
import com.sincco.service.event.usuario.UsuarioSalvoEvent;
import com.sincco.service.exception.CpfCnpjJaCadastradoException;
import com.sincco.service.exception.EmailJaCadastradoExcetion;

@Service
public class CadastroUsuarioService {
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public void salvar(Usuario usuario){
		
		Optional<Usuario> usuarioExistente = usuarios.findByCpfCnpj(usuario.getCpfCnpjSemFormatacao());
		if(usuarioExistente.isPresent()){
			throw new CpfCnpjJaCadastradoException("CPF/CNPJ já cadastro");
		}
		
		Optional<Usuario> usuarioEmailExistente = usuarios.findByEmailVendedor(usuario.getEmail());
		if(usuarioEmailExistente.isPresent()){
			throw new EmailJaCadastradoExcetion("E-mail já cadastrado");
		}
		
		usuarios.save(usuario);
		publisher.publishEvent(new UsuarioSalvoEvent(usuario));
	}
	
	@Transactional
	public List<Usuario> lista(){
		return usuarios.findAll();
	}
	
	@Transactional
	public void deletar(Usuario usuario){
		usuarios.delete(usuario);
	}
	
	public Usuario pesquisaPorId(Long id){
		return usuarios.findOne(id);
	}
	
}
