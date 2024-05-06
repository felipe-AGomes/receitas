package com.felipeagomes.receitas.services;

import com.felipeagomes.receitas.dtos.IngredientesDto;
import com.felipeagomes.receitas.dtos.ResponseIngredientesDto;
import com.felipeagomes.receitas.entities.Ingredientes;
import org.springframework.stereotype.Service;

@Service
public class IngredientesMapper {
    public ResponseIngredientesDto toResponseIngredientesDto(Ingredientes ingredientes) {
        return new ResponseIngredientesDto(
                ingredientes.getId(),
                ingredientes.getUsuario().getId(),
                ingredientes.getDescricao()
        );
    }

    public Ingredientes toIngredientes(IngredientesDto ingredientesDto) {
        return new Ingredientes(
                ingredientesDto.id(),
                ingredientesDto.descricao()
        );
    }
}