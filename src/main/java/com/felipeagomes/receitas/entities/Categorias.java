package com.felipeagomes.receitas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Categorias {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 4000)
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuarios usuario;

	@OneToMany
	@JoinColumn(name = "categoria_id")
	private List<ReceitasCategorias> receitas;

	public Categorias() {}

	public Categorias(long id, String descricao) {
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
	public List<ReceitasCategorias> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<ReceitasCategorias> receitas) {
		this.receitas = receitas;
	}
}