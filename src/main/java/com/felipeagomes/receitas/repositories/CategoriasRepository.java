package com.felipeagomes.receitas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipeagomes.receitas.entities.Categorias;

public interface CategoriasRepository extends JpaRepository<Categorias, Long> {

	List<Categorias> findAllByUsuarioId(long usuarioId);

}
