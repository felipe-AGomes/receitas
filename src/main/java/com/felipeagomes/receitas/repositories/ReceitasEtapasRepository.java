package com.felipeagomes.receitas.repositories;

import com.felipeagomes.receitas.entities.ReceitasEtapas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceitasEtapasRepository extends JpaRepository<ReceitasEtapas, Long> {
    List<ReceitasEtapas> getByUsuarioId(long usuarioId);
}
