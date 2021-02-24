package com.example.bddspringboot.entity;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Entity
@Table(schema = "perfil", name = "perfil_cliente")
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class PerfilCliente {

	@Id
	@Column(name= "id", updatable = false, nullable = false)
	private UUID id;
	
	@Column(name= "nombre", nullable = false)
	private String nombre;
	
	@Column(name= "fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	@Column(name= "email")
	private String email;
	
	@Column(name= "telefono")
	private String telefono;
	
}