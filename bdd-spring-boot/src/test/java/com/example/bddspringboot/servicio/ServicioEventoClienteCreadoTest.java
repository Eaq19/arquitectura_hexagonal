package com.example.bddspringboot.servicio;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bddspringboot.entity.PerfilCliente;
import com.example.bddspringboot.modelo.EventoClienteCreado;
import com.example.bddspringboot.repositorio.RepositorioPerfilCliente;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class ServicioEventoClienteCreadoTest {

	@MockBean
	private RepositorioPerfilCliente repositorio;
	
	@Autowired
	private ServicioEventoClienteCreado sut;
	
	@Test
	void dadoEventoClienteCreadoEntoncesRegistrarPerfilCliente() {
		//Given
		UUID id = UUID.randomUUID();
		EventoClienteCreado eventoClienteCreado = EventoClienteCreado.builder().id(id).nombre("Edison").fechaNacimiento(LocalDate.of(1995, 2, 28)).email("edison@hotmail.com").telefono("+57 1234567891").build();
		//When
		sut.registrar(eventoClienteCreado);
		//Then
		ArgumentCaptor<?> captor = ArgumentCaptor.forClass(PerfilCliente.class);
		verify(repositorio).save((PerfilCliente)captor.capture());
		PerfilCliente perfil = (PerfilCliente) captor.getValue();
		
		assertThat(perfil, is(not(nullValue())));
		assertThat(perfil.getId(), is(id));
		assertThat(perfil.getNombre(), is("Edison"));
		assertThat(perfil.getFechaNacimiento(), is(LocalDate.of(1995, 2, 28)));
		assertThat(perfil.getEmail(), is("edison@hotmail.com"));
		assertThat(perfil.getTelefono(), is("+57 1234567891"));
	}

}
