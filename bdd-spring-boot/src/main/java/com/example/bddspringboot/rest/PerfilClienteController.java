package com.example.bddspringboot.rest;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.bddspringboot.servicio.ServicioConsultaPerfilCliente;
import com.example.bddspringboot.vo.RespuestaPerfilCliente;

@RestController
public class PerfilClienteController {

	@Autowired
	private ServicioConsultaPerfilCliente servicio;
	
	@GetMapping("/cliente/perfil/{id}")
	public RespuestaPerfilCliente get(@PathVariable String id) {
		return this.servicio.consultar(UUID.fromString(id));
	}
	
}
