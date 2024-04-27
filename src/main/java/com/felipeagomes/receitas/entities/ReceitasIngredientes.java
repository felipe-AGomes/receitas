package com.felipeagomes.receitas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class ReceitasIngredientes {
    @EmbeddedId
    private ReceitasIngredientesId id;

	private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ReceitasIngredientesId getId() {
        return id;
    }

    public void setId(ReceitasIngredientesId id) {
        this.id = id;
    }
}
