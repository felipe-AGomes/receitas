package com.felipeagomes.receitas.dtos;

import com.felipeagomes.receitas.entities.Receitas;

public record ReceitasEtapasDto(long id, long receitaId, long seq, String descricao) {
}
