package com.felipeagomes.receitas.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Receitas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String descricao;
	private int preparacaoMinuto;
	@Column(length = 4000)
	private String obsLivre;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuarios usuario;

	@OneToMany
	@JoinColumn(name = "receita_id")
	private List<ReceitasCategorias> categorias;

	@OneToMany
	@JoinColumn(name = "receita_id")
	private List<ReceitasIngredientes> ingredientes;

	@OneToMany
	@JoinColumn(name = "receita_id")
	private List<ReceitasEtapas> etapas;

	@OneToMany
	@JoinColumn(name = "receita_id")
	private List<ReceitasImagens> imagens;

	public Receitas() {

	}

	public Receitas(Usuarios usuario, String descricao, String obsLivre, int preparacaoMinuto) {
		this.usuario = usuario;
		this.descricao = descricao;
		this.obsLivre = obsLivre;
		this.preparacaoMinuto = preparacaoMinuto;
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

	public int getPreparacaoMinuto() {
		return preparacaoMinuto;
	}

	public void setPreparacaoMinuto(int preparacaoMinuto) {
		this.preparacaoMinuto = preparacaoMinuto;
	}

	public String getObsLivre() {
		return obsLivre;
	}

	public void setObsLivre(String obsLivre) {
		this.obsLivre = obsLivre;
	}

	@JsonIgnore
	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public List<ReceitasCategorias> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<ReceitasCategorias> categorias) {
		this.categorias = categorias;
	}

	public List<ReceitasIngredientes> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<ReceitasIngredientes> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public List<ReceitasEtapas> getEtapas() {
		return etapas;
	}

	public void setEtapas(List<ReceitasEtapas> etapas) {
		this.etapas = etapas;
	}

	public List<ReceitasImagens> getImagens() {
		return imagens;
	}

	public void setImagens(List<ReceitasImagens> imagens) {
		this.imagens = imagens;
	}
}
