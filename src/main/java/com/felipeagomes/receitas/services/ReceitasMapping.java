package com.felipeagomes.receitas.services;

import com.felipeagomes.receitas.dtos.ReceitasDto;
import com.felipeagomes.receitas.dtos.ResponseReceitasDto;
import com.felipeagomes.receitas.entities.Receitas;
import com.felipeagomes.receitas.entities.Usuarios;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReceitasMapping {
    public ResponseReceitasDto toResponseReceitasDto(Receitas receitas) {
        return new ResponseReceitasDto(
                receitas.getId(),
                receitas.getDescricao(),
                receitas.getPreparacaoMinuto(),
                receitas.getObsLivre()
        );
    }

    public Receitas toReceitas(ReceitasDto receitaDto) {
        return new Receitas(
                receitaDto.descricao(),
                receitaDto.preparacaoMinuto(),
                receitaDto.obsLivre()
        );
    }
}
