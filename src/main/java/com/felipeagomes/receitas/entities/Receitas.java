package com.felipeagomes.receitas.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Receitas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String descricao;

	private int preparacaoMinuto;
	
	@JoinColumn(name = "usuario_id")
	@ManyToOne(optional = false)
	private Usuarios usuario; 

	@Column(length = 4000)
	private String obsLivre;

	@JoinTable(name = "receitas_categorias", joinColumns = @JoinColumn(name = "receita_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	@ManyToMany
	private List<Categorias> categorias;

	@OneToMany
	@JoinColumn(name = "receita_id")
	private List<ReceitasEtapas> etapas;

	@OneToMany
	@JoinTable(name = "ReceitasIngredientes", joinColumns = @JoinColumn(name = "receita_id"), inverseJoinColumns = @JoinColumn(name = "ingrediente_id"))
	private List<Ingredientes> ingredientes;

	public List<Ingredientes> getIngredientes() {
		return ingredientes;
	}
	
	@JsonIgnore
	public Usuarios getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public void setIngredientes(List<Ingredientes> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public List<ReceitasEtapas> getEtapas() {
		return etapas;
	}

	public void setEtapas(List<ReceitasEtapas> etapas) {
		this.etapas = etapas;
	}

	public List<Categorias> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categorias> categorias) {
		this.categorias = categorias;
	}

	public int getPreparacaoMinuto() {
		return preparacaoMinuto;
	}

	public void setPreparacaoMinuto(int timeInMinutes) {
		this.preparacaoMinuto = timeInMinutes;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		if (id == null) {
			return;
		}
		
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObsLivre() {
		return obsLivre;
	}

	public void setObsLivre(String obsLivre) {
		this.obsLivre = obsLivre;
	}
}
