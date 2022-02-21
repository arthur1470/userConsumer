package com.usuario.consumer;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.usuario.consumer.domain.Login;
import com.usuario.consumer.repository.TokenRepository;

@SpringBootApplication
public class UserRegisterConsumerApplication {

	public static void main(String[] args) {
		
		TokenRepository repo = TokenRepository.getInstance();
		Login login = new Login();
		login.setCpf("123");
		login.setSenha("123");
		
		repo.add(login);
		repo.getByCpf("123");
		
		
//		SpringApplication.run(UserRegisterConsumerApplication.class, args);
	}
}