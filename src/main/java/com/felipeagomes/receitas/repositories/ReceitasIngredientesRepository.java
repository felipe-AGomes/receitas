package com.felipeagomes.receitas.repositories;

import com.felipeagomes.receitas.entities.ReceitasIngredientes;
import com.felipeagomes.receitas.entities.ReceitasIngredientesId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitasIngredientesRepository extends JpaRepository<ReceitasIngredientes, ReceitasIngredientesId> {
}
