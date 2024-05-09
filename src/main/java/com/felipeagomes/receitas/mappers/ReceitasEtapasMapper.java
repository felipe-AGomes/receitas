package com.felipeagomes.receitas.mappers;

import com.felipeagomes.receitas.dtos.in.ReceitasEtapasDto;
import com.felipeagomes.receitas.dtos.out.ResponseReceitasEtapasDto;
import com.felipeagomes.receitas.entities.ReceitasEtapas;
import org.springframework.stereotype.Service;

@Service
public class ReceitasEtapasMapper {
    public ResponseReceitasEtapasDto toResponseReceitasEtapasDto(ReceitasEtapas receitasEtapas) {
        return new ResponseReceitasEtapasDto(
                receitasEtapas.getId(),
                receitasEtapas.getReceita().getId(),
                receitasEtapas.getSeq(),
                receitasEtapas.getDescricao()
        );
    }

    public ReceitasEtapas toReceitasEtapas(ReceitasEtapasDto receitasEtapasDto) {
        return new ReceitasEtapas(
                receitasEtapasDto.id(),
                receitasEtapasDto.seq(),
                receitasEtapasDto.descricao()
        );
    }
}
