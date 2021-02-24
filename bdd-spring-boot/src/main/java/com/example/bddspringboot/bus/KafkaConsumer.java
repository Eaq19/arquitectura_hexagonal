package com.example.bddspringboot.bus;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.bddspringboot.modelo.EventoClienteCreado;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaConsumer {

	@KafkaListener(topics = "${spring.kafka.topic.name}" + 1,
            concurrency = "${spring.kafka.consumer.level.concurrency:3}")
    public void logKafkaMessages(@Payload EventoClienteCreado evento,
                                 @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                 @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition,
                                 @Header(KafkaHeaders.OFFSET) Long offset) {
        log.info("Received a message contains a user information with id {}, from {} topic, " +
                        "{} partition, and {} offset", evento.getId(), topic, partition, offset);
    }
	
}
