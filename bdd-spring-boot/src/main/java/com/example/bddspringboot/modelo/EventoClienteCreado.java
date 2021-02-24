package com.example.bddspringboot.modelo;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class EventoClienteCreado {
	
	public UUID id;
	public String nombre;
	public LocalDate fechaNacimiento;
	public String email;
	public String telefono;
	
}
