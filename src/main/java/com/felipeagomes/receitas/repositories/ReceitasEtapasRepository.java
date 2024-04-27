package com.felipeagomes.receitas.repositories;

import com.felipeagomes.receitas.entities.ReceitasEtapas;
import com.felipeagomes.receitas.entities.ReceitasEtapasId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReceitasEtapasRepository extends JpaRepository<ReceitasEtapas, ReceitasEtapasId> {
    Optional<List<ReceitasEtapas>> findByIdReceitaId(long receitaId);
}
