package com.felipeagomes.receitas.services;

import com.felipeagomes.receitas.dtos.ReceitasIngredientesDto;
import com.felipeagomes.receitas.dtos.ResponseReceitasIngredientesDto;
import com.felipeagomes.receitas.entities.ReceitasIngredientes;
import com.felipeagomes.receitas.mappers.ReceitasIngredientesMapper;
import com.felipeagomes.receitas.repositories.ReceitasIngredientesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceitasIngredientesService {
    private final ReceitasIngredientesRepository receitasIngredientesRepository;
    private final ReceitasIngredientesMapper receitasIngredientesMapper;

    public ReceitasIngredientesService(ReceitasIngredientesRepository receitasIngredientesRepository, ReceitasIngredientesMapper receitasIngredientesMapper) {
        this.receitasIngredientesRepository = receitasIngredientesRepository;
        this.receitasIngredientesMapper = receitasIngredientesMapper;
    }

    public List<ResponseReceitasIngredientesDto> findAllByReceitaId(long receitaId) {
        Optional<List<ReceitasIngredientes>> optionalReceitasIngredientes = receitasIngredientesRepository.findByReceitaId(receitaId);
        return optionalReceitasIngredientes.map(receitasIngredientes -> receitasIngredientes.stream().map(receitasIngredientesMapper::toResponseReceitasIngredientesDto).toList()).orElse(null);
    }

    public ResponseReceitasIngredientesDto saveReceitaIngrediente(ReceitasIngredientesDto receitaIngredienteDto) {

        return null;
    }
}
