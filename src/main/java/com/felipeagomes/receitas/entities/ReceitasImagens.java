package com.felipeagomes.receitas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class ReceitasImagens {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true, nullable = false)
	private int seq;
	@Lob
	private byte[] imagem;

    @ManyToOne
    @JoinColumn(name = "receita_id", nullable = false)
    private Receitas receita;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
