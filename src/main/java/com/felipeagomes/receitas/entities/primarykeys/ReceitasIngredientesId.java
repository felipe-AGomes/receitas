package com.felipeagomes.receitas.entities.primarykeys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ReceitasIngredientesId implements Serializable {
    @Column(name = "receita_id")
    private long receitaId;
    @Column(name = "ingrediente_id")
    private long ingredienteId;

    public ReceitasIngredientesId() {}

    public ReceitasIngredientesId(long receitaId, long ingredienteId) {
        this.receitaId = receitaId;
        this.ingredienteId = ingredienteId;
    }

    public long getReceitaId() {
        return receitaId;
    }

    public void setReceitaId(long receitaId) {
        this.receitaId = receitaId;
    }

    public long getIngredienteId() {
        return ingredienteId;
    }

    public void setIngredienteId(long ingredienteId) {
        this.ingredienteId = ingredienteId;
    }
}
