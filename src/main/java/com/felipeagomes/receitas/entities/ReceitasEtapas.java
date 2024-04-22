package com.felipeagomes.receitas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class ReceitasEtapas {
	@EmbeddedId
    private ReceitasEtapasId id;

	@Column(length = 4000)
	private String descricao;

    @MapsId("receitaId")
    @ManyToOne
    @JoinColumn(name = "receita_id")
    private Receitas receita;

    public ReceitasEtapasId getId() {
        return id;
    }

    public void setId(ReceitasEtapasId id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @JsonIgnore
    public Receitas getReceita() {
        return receita;
    }

    public void setReceita(Receitas receita) {
        this.receita = receita;
    }
}
