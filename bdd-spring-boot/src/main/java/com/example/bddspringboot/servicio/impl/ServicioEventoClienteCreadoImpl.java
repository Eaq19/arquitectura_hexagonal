package com.example.bddspringboot.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bddspringboot.conversor.ConversorEventoClienteCreado;
import com.example.bddspringboot.modelo.EventoClienteCreado;
import com.example.bddspringboot.repositorio.RepositorioPerfilCliente;
import com.example.bddspringboot.servicio.ServicioEventoClienteCreado;

@Service
public class ServicioEventoClienteCreadoImpl implements ServicioEventoClienteCreado {

	@Autowired
	private RepositorioPerfilCliente repositorio;
	
	@Autowired
	private ConversorEventoClienteCreado conversor;
	
	@Override
	public void registrar(EventoClienteCreado eventoClienteCreado) {
		repositorio.save(conversor.convert(eventoClienteCreado));
	}

}
