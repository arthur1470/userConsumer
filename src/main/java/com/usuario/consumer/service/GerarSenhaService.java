package com.usuario.consumer.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.usuario.consumer.domain.Login;
import com.usuario.consumer.domain.Usuario;
import com.usuario.consumer.repository.TokenRepository;
import com.usuario.consumer.repository.UsuarioRepository;

@Service
public class GerarSenhaService {

	private UsuarioRepository usuarioRepository;
	private TokenRepository tokenRepository;
	
	public GerarSenhaService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
		this.tokenRepository = TokenRepository.getInstance();
	}

	public void gerarSenha(String cpf) throws Exception {
		Usuario usuario = validarTrazerUsuario(cpf);
		String senha = gerarSenhaDinamica();
		processarTokenLogin(cpf, senha);
		enviarSenhaDinamica(usuario, senha);
	}

	private Usuario validarTrazerUsuario(String cpf) throws Exception {
		Usuario usuario = usuarioRepository.findByCpf(cpf);
		
		if(usuario == null) {
			throw new Exception("Usuário não cadastrado.");
		}
		
		return usuario;
	}

	private String gerarSenhaDinamica() {
		String senha = UUID.randomUUID().toString();
		senha = senha.substring(0, 5);
		
		return senha;
	}
	
	private void processarTokenLogin(String cpf, String senha) {
		Login login = new Login(cpf, senha);
		
		tokenRepository.delete(login);
		tokenRepository.add(login);
	}
	
	private void enviarSenhaDinamica(Usuario usuario, String senhaDinamica) {
		System.out.println("E-mail: "+ usuario.getEmail() + "\nSua senha para logar é: " + senhaDinamica);
		System.out.println("SMS: "+ usuario.getTelefone() + "\nSua senha para logar é: " + senhaDinamica);
	}
}
