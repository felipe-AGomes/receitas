package com.felipeagomes.receitas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ReceitasCategoriasId implements Serializable {
    @Column(name = "receita_id")
    private long receitaId;
    @Column(name = "categoria_id")
    private long categoriaId;

    public long getReceitaId() {
        return receitaId;
    }

    public void setReceitaId(long receitaId) {
        this.receitaId = receitaId;
    }

    public long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
