package com.felipeagomes.receitas.mappers;

import com.felipeagomes.receitas.dtos.in.IngredientesDto;
import com.felipeagomes.receitas.dtos.out.ResponseIngredientesDto;
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
