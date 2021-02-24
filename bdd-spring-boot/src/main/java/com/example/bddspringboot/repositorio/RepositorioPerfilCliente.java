package com.example.bddspringboot.repositorio;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bddspringboot.entity.PerfilCliente;

@Repository
public interface RepositorioPerfilCliente extends JpaRepository<PerfilCliente, UUID> {
	
}
