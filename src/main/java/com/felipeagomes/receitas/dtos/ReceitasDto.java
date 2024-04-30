package com.felipeagomes.receitas.dtos;

import com.felipeagomes.receitas.entities.*;

import java.util.List;

public record ReceitasDto(
        String descricao,
        int preparacaoMinuto,
        String obsLivre,
        long usuarioId
) {
}
