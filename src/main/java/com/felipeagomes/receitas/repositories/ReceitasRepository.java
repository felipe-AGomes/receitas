package com.felipeagomes.receitas.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipeagomes.receitas.entities.Receitas;

public interface ReceitasRepository extends JpaRepository<Receitas, Long> {
	Optional<List<Receitas>> findByUsuarioId(long usuarioId);
}
