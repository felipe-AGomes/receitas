package com.felipeagomes.receitas.repositories;

import com.felipeagomes.receitas.entities.ReceitasEtapas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReceitasEtapasRepository extends JpaRepository<ReceitasEtapas, Long> {
    Optional<List<ReceitasEtapas>> findByReceitaId(long receitaId);
}
