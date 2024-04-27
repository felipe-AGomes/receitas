package com.felipeagomes.receitas.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ReceitasEtapasId implements Serializable {
    private long etapaId;
    private long receitaId;

    public long getEtapaId() {
        return etapaId;
    }

    public void setEtapaId(long etapaId) {
        this.etapaId = etapaId;
    }

    public long getReceitaId() {
        return receitaId;
    }

    public void setReceitaId(long receitaId) {
        this.receitaId = receitaId;
    }
}
