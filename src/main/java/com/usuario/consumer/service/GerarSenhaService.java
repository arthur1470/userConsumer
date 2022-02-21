package com.usuario.consumer.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.usuario.consumer.domain.Usuario;
import com.usuario.consumer.repository.UsuarioRepository;

@Service
public class GerarSenhaService {

	private UsuarioRepository usuarioRepository;
	
	public GerarSenhaService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public void gerarSenha(String cpf) throws Exception {
		Usuario usuario = usuarioRepository.findByCpf(cpf);
		
		if(usuario == null) {
			throw new Exception("Usuário não cadastrado.");
		}
		
		String senhaDinamica = gerarSenhaDinamica();
		enviarSenhaDinamica(usuario, senhaDinamica);
	}

	private String gerarSenhaDinamica() {
		String senha = UUID.randomUUID().toString();
		senha = senha.substring(0, 4);
		
		return senha;
	}
	
	private void enviarSenhaDinamica(Usuario usuario, String senhaDinamica) {
		System.out.println("E-mail: "+ usuario.getEmail() + "\nSua senha para logar é: " + senhaDinamica);
		System.out.println("SMS: "+ usuario.getTelefone() + "\nSua senha para logar é: " + senhaDinamica);
	}
}
