package com.usuario.consumer.consumer;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.usuario.consumer.domain.Usuario;
import com.usuario.consumer.service.RegistroService;

@Component
@KafkaListener(topics = {"${register.topic.name}"}, groupId = "${spring.kafka.group-id}", containerFactory = "registroKafkaListenerContainerFactory")
public class RegistroConsumer {

	private RegistroService registroService;	
	
	public RegistroConsumer(RegistroService registroService) {
		this.registroService = registroService;
	}

	@KafkaHandler
	public void consumeRegistroMessage(Usuario usuario) {
		try {
			registroService.registrarUsuario(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
