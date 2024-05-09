package com.felipeagomes.receitas.dtos.out;

public record ResponseReceitasDto(long id, long usuarioId, String descricao, int preparacaoMinuto, String obsLivre) {
}
