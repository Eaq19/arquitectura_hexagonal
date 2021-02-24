package com.example.bddspringboot.conversor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.bddspringboot.entity.PerfilCliente;
import com.example.bddspringboot.modelo.EventoClienteCreado;

@Component
public class ConversorEventoClienteCreado implements Converter <EventoClienteCreado, PerfilCliente> {

	@Override
	public PerfilCliente convert(EventoClienteCreado source) {
		return source == null ? null : PerfilCliente.builder().id(source.getId()).nombre(source.getNombre()).fechaNacimiento(source.getFechaNacimiento()).email(source.getEmail()).telefono(source.getTelefono()).build();
	}

}