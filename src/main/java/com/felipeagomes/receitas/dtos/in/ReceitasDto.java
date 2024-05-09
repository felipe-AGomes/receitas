package com.felipeagomes.receitas.dtos.in;

public record ReceitasDto(
        long id,
        long usuarioId,
        String descricao,
        int preparacaoMinuto,
        String obsLivre
) {
}
