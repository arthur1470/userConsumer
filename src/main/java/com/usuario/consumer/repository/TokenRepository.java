package com.usuario.consumer.repository;

import java.util.ArrayList;
import java.util.List;

import com.usuario.consumer.domain.Login;

public class TokenRepository {

	private List<Login> loginTokens;
	private static TokenRepository tokenRepository;
	
	private TokenRepository() {		
		loginTokens = new ArrayList<Login>();
	}
	
	public static TokenRepository getInstance() {
		if(tokenRepository == null) {
			tokenRepository = new TokenRepository();
		}
		return tokenRepository; 
	}
	
	public Login getByCpf(String cpf) {		
		for (Login login : loginTokens) {
			if(login.getCpf().equals(cpf))
				return login;
		}
		
		return null; 
	}
	
	public void add(Login login) {
		loginTokens.add(login);
	}
	
	public void delete(Login login) {
		loginTokens.remove(login);
	}
}
