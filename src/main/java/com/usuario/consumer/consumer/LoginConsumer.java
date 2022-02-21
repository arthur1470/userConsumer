package com.usuario.consumer.consumer;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.usuario.consumer.domain.Usuario;
import com.usuario.consumer.service.LoginService;

@Component
@KafkaListener(topics = {"${login.topic.name}" }, groupId = "${spring.kafka.group-id}", containerFactory = "loginKafkaListenerContainerFactory")
public class LoginConsumer {

	private LoginService loginService;

	public LoginConsumer(LoginService loginService) {
		this.loginService = loginService;
	}

	@KafkaHandler
	public void consumeRegistroMessage(Usuario usuario) {
		try {
			loginService.logar(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
