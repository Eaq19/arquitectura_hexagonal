package com.example.bddspringboot.repositorio;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.example.bddspringboot.entity.PerfilCliente;

@RunWith(SpringRunner.class)
@SpringBootTest
class RepositorioPerfilClienteIT {

	@Autowired
	private PlatformTransactionManager platformTransactionManager;
	
	@Autowired
	private RepositorioPerfilCliente sut;
	
	@Test
	void dadoQueGuardoUnRegistroEntoncesPuedoRecuperarlo() {
		//Given
		TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);
		UUID id = UUID.randomUUID();
		PerfilCliente perfilCliente = PerfilCliente.builder().id(id).nombre("Edison").fechaNacimiento(LocalDate.of(1995, 2, 28)).email("edison@hotmail.com").telefono("+57 1234567891").build();
		
		//When
		transactionTemplate.execute(s -> sut.save(perfilCliente));
		Optional<PerfilCliente> resultOptional = transactionTemplate.execute(s -> sut.findById(id));
		
		//Then
		PerfilCliente resultPerfilCliente = resultOptional.orElse(null);
		assertThat(resultPerfilCliente, is(not(nullValue())));
		assertThat(resultPerfilCliente.getId(), is(id));
		assertThat(resultPerfilCliente.getNombre(), is("Edison"));
		assertThat(resultPerfilCliente.getFechaNacimiento(), is(LocalDate.of(1995, 2, 28)));
		assertThat(resultPerfilCliente.getEmail(), is("edison@hotmail.com"));
		assertThat(resultPerfilCliente.getTelefono(), is("+57 1234567891"));
	}

}