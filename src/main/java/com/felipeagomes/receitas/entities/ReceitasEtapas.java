package com.felipeagomes.receitas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ReceitasEtapas {
	@Id
	@Column(nullable = false)
	private int seq;
	
	@Column(length = 4000)
	private String descricao;
	
	@Id
	@JoinColumn(name = "receita_id")
	@ManyToOne
	private Receitas receita;

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
