package com.felipeagomes.receitas.mappers;

import com.felipeagomes.receitas.dtos.in.ReceitasIngredientesDto;
import com.felipeagomes.receitas.dtos.out.ResponseReceitasIngredientesDto;
import com.felipeagomes.receitas.entities.ReceitasIngredientes;
import org.springframework.stereotype.Service;

@Service
public class ReceitasIngredientesMapper {
    public ResponseReceitasIngredientesDto toResponseReceitasIngredientesDto(ReceitasIngredientes receitaIngrediente) {
        return new ResponseReceitasIngredientesDto(
            receitaIngrediente.getReceita().getId(),
            receitaIngrediente.getIngrediente().getId(),
            receitaIngrediente.getDescricao(),
            receitaIngrediente.getIngrediente().getDescricao()
        );
    }

    public ReceitasIngredientes toReceitasIngredientes(ReceitasIngredientesDto receitaIngredienteDto) {
        return new ReceitasIngredientes(
                receitaIngredienteDto.receitaId(),
                receitaIngredienteDto.ingredienteId(),
                receitaIngredienteDto.descricao()
        );
    }
}
