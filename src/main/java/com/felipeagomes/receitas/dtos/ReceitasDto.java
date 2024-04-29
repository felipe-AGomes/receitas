package com.felipeagomes.receitas.dtos;

import com.felipeagomes.receitas.entities.*;

import java.util.List;

public record ReceitasDto(
        long id,
        String descricao,
        int preparacaoMinuto,
        String obsLivre,
        long usuarioId,
        List<ReceitasIngredientes> ingredientes,
        List<ReceitasEtapas> etapas,
        List<ReceitasCategorias> categorias,
        List<ReceitasImagens> imagens
) {
}
