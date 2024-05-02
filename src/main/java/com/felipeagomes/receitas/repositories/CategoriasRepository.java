package com.felipeagomes.receitas.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipeagomes.receitas.entities.Categorias;

public interface CategoriasRepository extends JpaRepository<Categorias, Long> {
	Optional<List<Categorias>> findAllByUsuarioId(long usuarioId);
}
