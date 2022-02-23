package com.usuario.consumer.service;

import org.springframework.stereotype.Service;

import com.usuario.consumer.domain.Usuario;
import com.usuario.consumer.repository.UsuarioRepository;

@Service
public class RegistroService {

	private UsuarioRepository usuarioRepository;

	public RegistroService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public void registrarUsuario(Usuario usuario) throws Exception {
		Usuario usuarioJaExiste = usuarioRepository.findByCpf(usuario.getCpf());
		
		if(usuarioJaExiste != null) {
			throw new Exception("Usuário já cadastrado.");
		}
		
		
		usuarioRepository.save(usuario);
	}
}
