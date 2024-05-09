package com.felipeagomes.receitas.dtos.out;

public record ResponseReceitasIngredientesDto(long receitaId, long ingredienteId, String descricao,
                                              String ingrediente) {
}
