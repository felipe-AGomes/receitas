package com.felipeagomes.receitas.mappers;

import com.felipeagomes.receitas.dtos.ReceitasDto;
import com.felipeagomes.receitas.dtos.ResponseReceitasDto;
import com.felipeagomes.receitas.entities.Receitas;
import org.springframework.stereotype.Service;

@Service
public class ReceitasMapper {
    public ResponseReceitasDto toResponseReceitasDto(Receitas receitas) {
        return new ResponseReceitasDto(
                receitas.getId(),
                receitas.getUsuario().getId(),
                receitas.getDescricao(),
                receitas.getPreparacaoMinuto(),
                receitas.getObsLivre()
        );
    }

    public Receitas toReceitas(ReceitasDto receitaDto) {
        return new Receitas(
                receitaDto.id(),
                receitaDto.descricao(),
                receitaDto.preparacaoMinuto(),
                receitaDto.obsLivre()
        );
    }
}
