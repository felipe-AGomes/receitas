package com.felipeagomes.receitas.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ReceitasEtapasId implements Serializable {
    private long seq;
    private long receitaId;

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    public long getReceitaId() {
        return receitaId;
    }

    public void setReceitaId(long receitaId) {
        this.receitaId = receitaId;
    }
}
