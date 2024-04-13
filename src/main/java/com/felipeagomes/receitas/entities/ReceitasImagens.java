package com.felipeagomes.receitas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class ReceitasImagens {
	@Id
	private long id;
	@Column(unique = true, nullable = false)
	private int seq;
	@Lob
	private byte[] imagem;
	@JoinColumn
	@ManyToOne
	private Receitas receita;

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getSeq() {
		return seq;
	}
	
	public void setSeq(int seq) {
		this.seq = seq;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public Receitas getReceita() {
		return receita;
	}

	public void setReceita(Receitas receita) {
		this.receita = receita;
	}
}
