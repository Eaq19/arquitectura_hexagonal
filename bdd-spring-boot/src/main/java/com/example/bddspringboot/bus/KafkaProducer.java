package com.example.bddspringboot.bus;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.bddspringboot.modelo.EventoClienteCreado;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class KafkaProducer {

	@Value("${spring.kafka.topic.name}")
	private String topic;
	
	@Value("${spring.kafka.replication.factor:1}")
	private int replicationFactor;
	
	@Value("${spring.kafka.partition.number:1}")
	private int partitionNumber;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void writeToKakfka(EventoClienteCreado evento) throws JsonProcessingException {
		kafkaTemplate.send(topic, evento.id.toString(), objectMapper.writeValueAsString(evento));
	}
	
	@Bean
	@Order(-1)
	public NewTopic createNewTopic() {
		return new NewTopic(topic, partitionNumber, (short) replicationFactor);
	}
}
