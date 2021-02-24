package com.example.bddspringboot.conversor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.bddspringboot.entity.PerfilCliente;
import com.example.bddspringboot.vo.RespuestaPerfilCliente;

@Component
public class ConversorPerfilCliente implements Converter<PerfilCliente, RespuestaPerfilCliente> {

	@Override
	public RespuestaPerfilCliente convert(PerfilCliente source) {
		return source == null ? null : RespuestaPerfilCliente.builder().id(source.getId()).nombre(source.getNombre()).email(source.getEmail()).telefono(source.getTelefono()).fechaNacimiento(source.getFechaNacimiento()).build();
	}

}
