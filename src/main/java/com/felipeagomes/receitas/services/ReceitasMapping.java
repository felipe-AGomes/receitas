package com.felipeagomes.receitas.services;

import com.felipeagomes.receitas.dtos.ReceitasDto;
import com.felipeagomes.receitas.entities.Receitas;
import com.felipeagomes.receitas.entities.Usuarios;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReceitasMapping {
    public ReceitasDto toReceitasDto(Receitas receitas) {
        return new ReceitasDto(
                receitas.getId(),
                receitas.getDescricao(),
                receitas.getPreparacaoMinuto(),
                receitas.getObsLivre(),
                receitas.getUsuario().getId(),
                receitas.getIngredientes(),
                receitas.getEtapas(),
                receitas.getCategorias(),
                receitas.getImagens()
        );
    }
}
