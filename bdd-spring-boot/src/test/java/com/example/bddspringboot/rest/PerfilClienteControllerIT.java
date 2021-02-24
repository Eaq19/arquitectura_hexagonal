package com.example.bddspringboot.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bddspringboot.servicio.ServicioConsultaPerfilCliente;
import com.example.bddspringboot.vo.RespuestaPerfilCliente;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PerfilClienteControllerIT {
	
	@MockBean
	private ServicioConsultaPerfilCliente servicio;
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void dadoIdClienteExistenteEntoncesDevuelvePerfilCliente() {
		//Given
		UUID id = UUID.randomUUID();
		RespuestaPerfilCliente perfil = RespuestaPerfilCliente.builder().id(id).nombre("Edison").fechaNacimiento(LocalDate.of(1995, 2, 28)).email("edison@hotmail.com").telefono("+57 1234567891").build();
		doReturn(perfil).when(servicio).consultar(id);
		
		//When
		ResponseEntity<RespuestaPerfilCliente> result = restTemplate.getForEntity("/cliente/perfil/{id}", RespuestaPerfilCliente.class, id);
		 
		//Then
		assertThat(result.getStatusCode(), is(HttpStatus.OK));
		RespuestaPerfilCliente respuesta = result.getBody();
		assertThat(respuesta, is(not(nullValue())));
		assertThat(respuesta.getId(), is(id));
		assertThat(respuesta.getNombre(), is("Edison"));
		assertThat(respuesta.getFechaNacimiento(), is(LocalDate.of(1995, 2, 28)));
		assertThat(respuesta.getEmail(), is("edison@hotmail.com"));
		assertThat(respuesta.getTelefono(), is("+57 1234567891"));
		
	}

}