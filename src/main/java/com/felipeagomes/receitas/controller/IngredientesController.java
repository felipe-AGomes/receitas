package com.felipeagomes.receitas.controller;

import java.util.List;
import java.util.Optional;

import com.felipeagomes.receitas.entities.Usuarios;
import com.felipeagomes.receitas.repositories.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.felipeagomes.receitas.entities.Ingredientes;
import com.felipeagomes.receitas.repositories.IngredientesRepository;

@RestController
@RequestMapping("/secure/ingredientes")
public class IngredientesController {
	@Autowired
	private IngredientesRepository ingredientesRepository;
	@Autowired
	private UsuariosRepository usuariosRepository;

	@GetMapping
	private List<Ingredientes> findAll(@RequestHeader long usuarioId) {
		return ingredientesRepository.findAllByUsuarioId(usuarioId);
	}

	@PostMapping
	private Ingredientes createIngrediente(@RequestHeader long usuarioId, @RequestBody Ingredientes ingrediente) {
		Optional<Usuarios> usuario = usuariosRepository.findById(usuarioId);

		if (usuario.isPresent()) {
			ingrediente.setUsuario(usuario.get());
			return ingredientesRepository.save(ingrediente);
		}

		return null;
	}

	@PutMapping
	private Ingredientes updateIngrediente(@RequestHeader long id, @RequestHeader long usuarioId, @RequestBody Ingredientes ingrediente) {
		Optional<Usuarios> usuario = usuariosRepository.findById(usuarioId);

		if (usuario.isPresent()) {
			ingrediente.setId(id);
			ingrediente.setUsuario(usuario.get());
			return ingredientesRepository.save(ingrediente);
		}

		return null;
	}

	@DeleteMapping
	private void deleteIngrediente(@RequestHeader long id, @RequestHeader long usuarioId) {
		Optional<Usuarios> usuario = usuariosRepository.findById(usuarioId);

		if (usuario.isPresent()) {
			ingredientesRepository.deleteById(id);
		}
	}
}
