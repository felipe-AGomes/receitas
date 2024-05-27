package com.felipeagomes.receitas.services;

import com.felipeagomes.receitas.dtos.in.ReceitasIngredientesDto;
import com.felipeagomes.receitas.dtos.out.ResponseReceitasIngredientesDto;
import com.felipeagomes.receitas.entities.Ingredientes;
import com.felipeagomes.receitas.entities.Receitas;
import com.felipeagomes.receitas.entities.ReceitasIngredientes;
import com.felipeagomes.receitas.exceptions.ReceitaOrIngredienteNotFoundException;
import com.felipeagomes.receitas.mappers.ReceitasIngredientesMapper;
import com.felipeagomes.receitas.repositories.IngredientesRepository;
import com.felipeagomes.receitas.repositories.ReceitasIngredientesRepository;
import com.felipeagomes.receitas.repositories.ReceitasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceitasIngredientesService {
    private final ReceitasRepository receitasRepository;
    private final IngredientesRepository ingredientesRepository;
    private final ReceitasIngredientesRepository receitasIngredientesRepository;
    private final ReceitasIngredientesMapper receitasIngredientesMapper;

    public ReceitasIngredientesService(ReceitasRepository receitasRepository, IngredientesRepository ingredientesRepository, ReceitasIngredientesRepository receitasIngredientesRepository, ReceitasIngredientesMapper receitasIngredientesMapper) {
        this.receitasRepository = receitasRepository;
        this.ingredientesRepository = ingredientesRepository;
        this.receitasIngredientesRepository = receitasIngredientesRepository;
        this.receitasIngredientesMapper = receitasIngredientesMapper;
    }

    public List<ResponseReceitasIngredientesDto> findAllByReceitaId(long receitaId) {
        Optional<List<ReceitasIngredientes>> optionalReceitasIngredientes = receitasIngredientesRepository.findByReceitaId(receitaId);
        return optionalReceitasIngredientes.map(receitasIngredientes -> receitasIngredientes.stream().map(receitasIngredientesMapper::toResponseReceitasIngredientesDto).toList()).orElse(null);
    }

    public ResponseReceitasIngredientesDto saveReceitaIngrediente(ReceitasIngredientesDto receitaIngredienteDto) {
        Optional<Receitas> receitaOptional = receitasRepository.findById(receitaIngredienteDto.receitaId());
        Optional<Ingredientes> ingredienteOptional = ingredientesRepository.findById(receitaIngredienteDto.ingredienteId());

        if (receitaOptional.isPresent() && ingredienteOptional.isPresent()) {
            ReceitasIngredientes receitaIngrediente = receitasIngredientesMapper.toReceitasIngredientes(receitaIngredienteDto);
            receitaIngrediente.setReceita(receitaOptional.get());
            receitaIngrediente.setIngrediente(ingredienteOptional.get());

            receitasIngredientesRepository.save(receitaIngrediente);

            return receitasIngredientesMapper.toResponseReceitasIngredientesDto(receitaIngrediente);
        }

        throw new ReceitaOrIngredienteNotFoundException();
    }
}
