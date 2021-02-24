package com.example.bddspringboot.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.bddspringboot.modelo.EventoClienteCreado;
import com.example.bddspringboot.servicio.ServicioEventoClienteCreado;

@Component
public class ConsumidorEventoClienteCreado {

	@Autowired
    private ServicioEventoClienteCreado servicio;
	
	@KafkaListener(topics = "${spring.kafka.topic.name}",
            concurrency = "${spring.kafka.consumer.level.concurrency:3}")
    public void procesarEvento(@Payload EventoClienteCreado evento, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        servicio.registrar(evento);
    }
}
