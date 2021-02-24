package com.example.bddspringboot.servicio;

import java.util.UUID;

import com.example.bddspringboot.vo.RespuestaPerfilCliente;

public interface ServicioConsultaPerfilCliente {

	RespuestaPerfilCliente consultar(UUID idCliente);
	
}
