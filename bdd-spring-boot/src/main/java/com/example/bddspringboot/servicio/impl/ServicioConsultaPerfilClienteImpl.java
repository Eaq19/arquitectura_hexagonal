package com.example.bddspringboot.servicio.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bddspringboot.conversor.ConversorPerfilCliente;
import com.example.bddspringboot.entity.PerfilCliente;
import com.example.bddspringboot.repositorio.RepositorioPerfilCliente;
import com.example.bddspringboot.servicio.ServicioConsultaPerfilCliente;
import com.example.bddspringboot.vo.RespuestaPerfilCliente;

@Service
public class ServicioConsultaPerfilClienteImpl implements ServicioConsultaPerfilCliente {

	@Autowired
	private RepositorioPerfilCliente repositorio;
	
	@Autowired
    private ConversorPerfilCliente conversor;

	@Override
	public RespuestaPerfilCliente consultar(UUID idCliente) {
		Optional<PerfilCliente> perfil = repositorio.findById(idCliente);
		return conversor.convert(perfil.orElse(null));
	}

}
