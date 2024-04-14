package com.felipeagomes.receitas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipeagomes.receitas.entities.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long>{
	Usuarios findByEmail(String email);
}
