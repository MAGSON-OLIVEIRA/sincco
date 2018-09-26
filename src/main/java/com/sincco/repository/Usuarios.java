package com.sincco.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sincco.model.Usuario;
import com.sincco.repository.helper.usuario.UsuariosQueries;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries {
	//public Optional<Usuario> findByEmail(String email);
	//@Query("select u from Usuario u where u.email = ? and u.idTipoUsuario = 'VENDEDOR'")
	@Query("select u from Usuario u where u.email = ? ")
	public Optional<Usuario> findByEmailVendedor(String email);

	public Optional<Usuario> findByCpfCnpj(String cpfCnpj);


	
}
