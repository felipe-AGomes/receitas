package com.felipeagomes.receitas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Ingredientes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String descricao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuario;

	@OneToMany
	@JoinColumn(name = "ingrediente_id")
	private List<ReceitasIngredientes> receitas;

	public Ingredientes() {}

    public Ingredientes(long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
    }

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@JsonIgnore
	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	@JsonIgnore
	public List<ReceitasIngredientes> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<ReceitasIngredientes> receitas) {
		this.receitas = receitas;
	}
}
