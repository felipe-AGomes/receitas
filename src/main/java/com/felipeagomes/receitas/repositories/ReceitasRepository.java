package com.felipeagomes.receitas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipeagomes.receitas.entities.Receitas;

public interface ReceitasRepository extends JpaRepository<Receitas, Long> {
	List<Receitas> findByUsuarioId(long usuarioId);
}
