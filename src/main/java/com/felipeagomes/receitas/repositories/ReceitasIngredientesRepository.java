package com.felipeagomes.receitas.repositories;

import com.felipeagomes.receitas.entities.ReceitasIngredientes;
import com.felipeagomes.receitas.entities.primarykeys.ReceitasIngredientesId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReceitasIngredientesRepository extends JpaRepository<ReceitasIngredientes, ReceitasIngredientesId> {
    Optional<List<ReceitasIngredientes>> findByReceitaId(long receita_id);
}
