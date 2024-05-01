package com.felipeagomes.receitas.dtos;

import com.felipeagomes.receitas.entities.*;

import java.util.List;

public record ReceitasDto(
        long id,
        long usuarioId,
        String descricao,
        int preparacaoMinuto,
        String obsLivre
) {
}
