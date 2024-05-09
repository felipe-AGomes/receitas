package com.felipeagomes.receitas.services;

import com.felipeagomes.receitas.dtos.ResponseReceitasIngredientesDto;
import com.felipeagomes.receitas.entities.ReceitasIngredientes;
import com.felipeagomes.receitas.repositories.ReceitasIngredientesRepository;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JsonParser;
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

        if (optionalReceitasIngredientes.isPresent()) {
            List<ReceitasIngredientes> receitasIngredientes = optionalReceitasIngredientes.get();

            return receitasIngredientes.stream().map(receitasIngredientesMapper::toResponseReceitasIngredientesDto).toList();
        }

        return null;
    }
}
