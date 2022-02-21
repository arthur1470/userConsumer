package com.usuario.consumer.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.usuario.consumer.domain.Login;
import com.usuario.consumer.domain.Usuario;
import com.usuario.consumer.repository.TokenRepository;
import com.usuario.consumer.repository.UsuarioRepository;

@Service
public class LoginService {

	private UsuarioRepository usuarioRepository;
	private TokenRepository tokenRepository;
	
	public LoginService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
		this.tokenRepository = TokenRepository.getInstance();
	}
	
	public Usuario logar(Login login) throws Exception {
		validarLogin(login);		
		
		Usuario usuario = usuarioRepository.findByCpf(login.getCpf());
		
		return usuario;
	}
	
	private void validarLogin(Login login) throws Exception {
		Login loginToken = tokenRepository.getByCpf(login.getCpf());
		
		if(loginToken == null)
			throw new Exception("Usuário inválido!");
		
		if(!login.getSenha().equals(loginToken.getSenha())) 
			throw new Exception("Senha incorreta!");
		
		if(loginToken.getDataExpiracao().isBefore(LocalDateTime.now()))
			throw new Exception("Token expirado!");		
	}

}
