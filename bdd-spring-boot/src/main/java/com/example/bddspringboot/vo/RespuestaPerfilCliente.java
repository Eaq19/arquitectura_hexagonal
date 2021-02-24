package com.example.bddspringboot.vo;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RespuestaPerfilCliente {

	private UUID id;
	private String nombre;
	private LocalDate fechaNacimiento;
	private String email;
	private String telefono;
	
}
