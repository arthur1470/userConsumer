package com.usuario.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usuario.consumer.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByCpf(String cpf);
}
