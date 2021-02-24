package com.example.bddspringboot.bus;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bddspringboot.modelo.EventoClienteCreado;
import com.example.bddspringboot.servicio.ServicioEventoClienteCreado;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@EmbeddedKafka
@SpringBootTest(properties = "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConsumidorEventoClienteCreadoIT {

	@Value("${spring.kafka.topic.name}")
	private String topic;

	@Autowired
	private EmbeddedKafkaBroker embeddedKafkaBroker;

	private Producer<String, String> producer;

	@Autowired
	private ObjectMapper objectMapper;

	@SpyBean
	private ConsumidorEventoClienteCreado consumidor;

	@Captor
	ArgumentCaptor<String> topicArgumentCaptor;
	
	@MockBean
    private ServicioEventoClienteCreado servicio;

	@BeforeAll
	void setUp() {
		Map<String, Object> configs = new HashMap<>(KafkaTestUtils.producerProps(embeddedKafkaBroker));
		producer = new DefaultKafkaProducerFactory<>(configs, new StringSerializer(), new StringSerializer())
				.createProducer();
	}

	@AfterAll
	void tearDown() {
		producer.close();
	}

	@Test
	void dadoEventoClienteCreadoEntoncesRegistraPerfilCliente() throws JsonProcessingException {
		// Given
		UUID id = UUID.randomUUID();
		EventoClienteCreado evento = EventoClienteCreado.builder().id(id).nombre("Edison")
				.fechaNacimiento(LocalDate.of(1995, 2, 28)).email("edison@hotmail.com").telefono("+57 1234567891")
				.build();
		String uuid = id.toString();
		String message = objectMapper.writeValueAsString(evento);

		// When
		producer.send(new ProducerRecord<>(topic, 0, uuid, message));
		producer.flush();

		// Then
		ArgumentCaptor<EventoClienteCreado> eventoCaptor = ArgumentCaptor.forClass(EventoClienteCreado.class);
		verify(consumidor, timeout(10000).times(1)).procesarEvento(eventoCaptor.capture(),
				topicArgumentCaptor.capture());

		EventoClienteCreado result = eventoCaptor.getValue();

		assertThat(result, is(not(nullValue())));
		assertThat(result.getId(), is(id));
		assertThat(result.getNombre(), is("Edison"));
		assertThat(result.getFechaNacimiento(), is(LocalDate.of(1995, 2, 28)));
		assertThat(result.getEmail(), is("edison@hotmail.com"));
		assertThat(result.getTelefono(), is("+57 1234567891"));
		assertThat(topic, is(topicArgumentCaptor.getValue()));

		eventoCaptor = ArgumentCaptor.forClass(EventoClienteCreado.class);
        verify(servicio, timeout(10000)).registrar(eventoCaptor.capture());
        result = eventoCaptor.getValue();

        assertThat(result, is(not(nullValue())));
		assertThat(result.getId(), is(id));
		assertThat(result.getNombre(), is("Edison"));
		assertThat(result.getFechaNacimiento(), is(LocalDate.of(1995, 2, 28)));
		assertThat(result.getEmail(), is("edison@hotmail.com"));
		assertThat(result.getTelefono(), is("+57 1234567891"));
	}

}