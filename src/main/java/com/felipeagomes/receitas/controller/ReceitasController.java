package com.felipeagomes.receitas.controller;

import java.util.List;
import java.util.Optional;

import com.felipeagomes.receitas.entities.Categorias;
import com.felipeagomes.receitas.repositories.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipeagomes.receitas.entities.Receitas;
import com.felipeagomes.receitas.entities.Usuarios;
import com.felipeagomes.receitas.repositories.ReceitasRepository;
import com.felipeagomes.receitas.repositories.UsuariosRepository;

@RestController
@RequestMapping("/secure")
public class ReceitasController {
	@Autowired
	private ReceitasRepository receitasRepository;
	@Autowired
	private UsuariosRepository usuariosRepository;
	@Autowired
	private CategoriasRepository categoriasRepository;

	@GetMapping("/receitas")
	public List<Receitas> findAll(@RequestHeader long usuarioId) {
		return receitasRepository.findByUsuarioId(usuarioId);
	}

	@PostMapping("/receitas")
	public Receitas createReceita(@RequestBody Receitas receita, @RequestHeader long usuarioId) {
		Optional<Usuarios> usuario = usuariosRepository.findById(usuarioId);

		if (usuario.isPresent()) {
			receita.setUsuario(usuario.get());
			receitasRepository.save(receita);
		}

		return receita;
	}

	@PutMapping("/receitas")
	public Receitas updateReceita(@RequestBody Receitas receita, @RequestHeader long id, @RequestHeader long usuarioId) {
		Optional<Usuarios> usuario = usuariosRepository.findById(usuarioId);

		if (usuario.isPresent()) {
			receita.setUsuario(usuario.get());
			receita.setId(id);
			return receitasRepository.save(receita);
		}

		return null;
	}

	@DeleteMapping("/receitas")
	public void deleteReceita(@RequestHeader long id, @RequestHeader long usuarioId) {
		Optional<Usuarios> usuario = usuariosRepository.findById(usuarioId);

		if (usuario.isPresent()) {
			receitasRepository.deleteById(id);
		}
	}

	@GetMapping("/receitascategorias")
	public List<Categorias> findAllCategorias(@RequestHeader long receitaId) {
		Optional<Receitas> receita = receitasRepository.findById(receitaId);

        return receita.map(Receitas::getCategorias).orElse(null);

    }

	@PostMapping("/receitascategorias")
	public Categorias createReceitaCategoria(@RequestHeader long receitaId, @RequestHeader long categoriaId) {
		Optional<Receitas> receita = receitasRepository.findById(receitaId);
		Optional<Categorias> categoria = categoriasRepository.findById(categoriaId);

		if (receita.isPresent() && categoria.isPresent()) {
			receita.get().addCategoria(categoria.get());
			receitasRepository.save(receita.get());

			return categoria.get();
		}

		return null;
	}

	@PutMapping("/receitascategorias")
	public void updateReceitaCategoria(@RequestHeader long receitaId, @RequestHeader long categoriaId, @RequestBody Categorias categorias) {
		Optional<Receitas> receita = receitasRepository.findById(receitaId);
		Optional<Categorias> categoria = categoriasRepository.findById(categoriaId);

		if (receita.isPresent() && categoria.isPresent()) {
			receita.get().deleteCategoria(categoria.get());

			receita.get().addCategoria(categorias);
			receitasRepository.save(receita.get());
		}
	}

	@DeleteMapping("/receitascategorias")
	public void deleteReceitaCategoria(@RequestHeader long receitaId, @RequestHeader long categoriaId) {
		Optional<Receitas> receita = receitasRepository.findById(receitaId);
		Optional<Categorias> categoria = categoriasRepository.findById(categoriaId);

		if (receita.isPresent() && categoria.isPresent()) {
			receita.get().deleteCategoria(categoria.get());
			receitasRepository.save(receita.get());
		}
	}
}
