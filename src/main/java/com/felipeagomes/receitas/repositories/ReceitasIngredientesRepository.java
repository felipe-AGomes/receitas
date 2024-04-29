package com.felipeagomes.receitas.repositories;

import com.felipeagomes.receitas.entities.ReceitasIngredientes;
import com.felipeagomes.receitas.entities.primarykeys.ReceitasIngredientesId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceitasIngredientesRepository extends JpaRepository<ReceitasIngredientes, ReceitasIngredientesId> {
    List<ReceitasIngredientes> findByReceitaId(long receita_id);
}
