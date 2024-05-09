package com.felipeagomes.receitas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.felipeagomes.receitas.entities.primarykeys.ReceitasCategoriasId;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class ReceitasCategorias {
    @EmbeddedId
    private ReceitasCategoriasId id;

    @ManyToOne
    @MapsId("receitaId")
    @JoinColumn(name = "receita_id")
    private Receitas receita;

    @ManyToOne
    @MapsId("categoriaId")
    @JoinColumn(name = "categoria_id")
    private Categorias categoria;

    public ReceitasCategoriasId getId() {
        return id;
    }

    public void setId(ReceitasCategoriasId id) {
        this.id = id;
    }

    public Receitas getReceita() {
        return receita;
    }

    public void setReceita(Receitas receita) {
        this.receita = receita;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }
}
