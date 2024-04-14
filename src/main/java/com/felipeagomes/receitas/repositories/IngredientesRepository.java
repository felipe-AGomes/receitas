package com.felipeagomes.receitas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipeagomes.receitas.entities.Ingredientes;

public interface IngredientesRepository extends JpaRepository<Ingredientes, Long>{

	List<Ingredientes> findAllByUsuarioId(long usuarioId);
}
