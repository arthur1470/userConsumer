package com.usuario.consumer.consumer;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.usuario.consumer.service.GerarSenhaService;

@Component
@KafkaListener(topics = {"${dynamicPassword.topic.name}"}, groupId = "${spring.kafka.group-id}", containerFactory = "gerarSenhaKafkaListenerContainerFactory")
public class GerarSenhaConsumer {

	private GerarSenhaService gerarSenhaService;

	public GerarSenhaConsumer(GerarSenhaService gerarSenhaService) {
		this.gerarSenhaService = gerarSenhaService;
	}
	
	@KafkaHandler
	public void consumeGerarSenhaMessage(String cpf) {
		try {
			gerarSenhaService.gerarSenha(cpf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
