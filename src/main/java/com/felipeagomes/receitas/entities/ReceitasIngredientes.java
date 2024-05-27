package com.felipeagomes.receitas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.felipeagomes.receitas.entities.primarykeys.ReceitasIngredientesId;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class ReceitasIngredientes {
    @EmbeddedId
    private ReceitasIngredientesId id;

    @ManyToOne
    @MapsId("receitaId")
    @JoinColumn(name = "receita_id")
    private Receitas receita;

    @ManyToOne
    @MapsId("ingredienteId")
    @JoinColumn(name = "ingrediente_id")
    private Ingredientes ingrediente;

    private String descricao;

    public ReceitasIngredientes() {}

    public ReceitasIngredientes(long receita, long ingrediente, String descricao) {
        this.id = new ReceitasIngredientesId(receita, ingrediente);
        this.descricao = descricao;
    }

    public Receitas getReceita() {
        return receita;
    }

    public void setReceita(Receitas receita) {
        this.receita = receita;
    }

    public Ingredientes getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingredientes ingrediente) {
        this.ingrediente = ingrediente;
    }

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
