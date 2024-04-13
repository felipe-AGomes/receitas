package com.felipeagomes.receitas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ReceitasIngredientes {
	@Id
	@JoinColumn
	@ManyToOne
	private Receitas receita;
	@Id
	@JoinColumn
	@ManyToOne
	private Ingredientes ingrediente;
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
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
}
