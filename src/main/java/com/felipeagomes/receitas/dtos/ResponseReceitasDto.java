package com.felipeagomes.receitas.dtos;

public record ResponseReceitasDto(long id, long usuarioId, String descricao, int preparacaoMinuto, String obsLivre) {
}
