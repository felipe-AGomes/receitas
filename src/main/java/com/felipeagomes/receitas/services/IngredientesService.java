package com.felipeagomes.receitas.services;

import com.felipeagomes.receitas.dtos.ResponseIngredientesDto;
import com.felipeagomes.receitas.entities.Ingredientes;
import com.felipeagomes.receitas.repositories.IngredientesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientesService {
    private final IngredientesRepository ingredientesRepository;
    private final IngredientesMapper ingredientesMapper;

    public IngredientesService(IngredientesRepository ingredientesRepository, IngredientesMapper ingredientesMapper) {
        this.ingredientesRepository = ingredientesRepository;
        this.ingredientesMapper = ingredientesMapper;
    }

    public List<ResponseIngredientesDto> findAllByUsuarioId(long usuarioId) {
        Optional<List<Ingredientes>> optionalIngredientes = ingredientesRepository.findAllByUsuarioId(usuarioId);

        return optionalIngredientes.map((optionalIngreciente) -> optionalIngreciente.stream().map(ingredientesMapper::toResponseIngredientesDto).toList()).orElse(null);
    }
}
