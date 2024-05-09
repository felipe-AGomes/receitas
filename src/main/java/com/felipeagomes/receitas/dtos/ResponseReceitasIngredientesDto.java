package com.felipeagomes.receitas.dtos;

public record ResponseReceitasIngredientesDto(long receitaId, long ingredienteId, String descricao,
                                              String ingrediente) {
}
