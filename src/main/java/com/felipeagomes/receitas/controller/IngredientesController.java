package com.felipeagomes.receitas.controller;

import java.util.List;
import java.util.Optional;

import com.felipeagomes.receitas.dtos.IngredientesDto;
import com.felipeagomes.receitas.dtos.ResponseIngredientesDto;
import com.felipeagomes.receitas.entities.Usuarios;
import com.felipeagomes.receitas.repositories.UsuariosRepository;
import com.felipeagomes.receitas.services.IngredientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.felipeagomes.receitas.entities.Ingredientes;
import com.felipeagomes.receitas.repositories.IngredientesRepository;

@RestController
@RequestMapping("/secure/ingredientes")
public class IngredientesController {
	private final IngredientesService ingredientesService;

    public IngredientesController(IngredientesService ingredientesService) {
        this.ingredientesService = ingredientesService;
    }

	@GetMapping
	private List<ResponseIngredientesDto> findAllByUsuarioId(@RequestHeader long usuarioId) {
		return ingredientesService.findAllByUsuarioId(usuarioId);
	}

	@PostMapping
	private ResponseIngredientesDto saveIngrediente(@RequestBody IngredientesDto ingredientesDto) {
		return ingredientesService.saveIngrediente(ingredientesDto);
	}
//
//	@PutMapping
//	private Ingredientes updateIngrediente(@RequestHeader long id, @RequestHeader long usuarioId, @RequestBody Ingredientes ingrediente) {
//		Optional<Usuarios> usuario = usuariosRepository.findById(usuarioId);
//
//		if (usuario.isPresent()) {
//			ingrediente.setId(id);
//			ingrediente.setUsuario(usuario.get());
//			return ingredientesRepository.save(ingrediente);
//		}
//
//		return null;
//	}
//
//	@DeleteMapping
//	private void deleteIngrediente(@RequestHeader long id, @RequestHeader long usuarioId) {
//		Optional<Usuarios> usuario = usuariosRepository.findById(usuarioId);
//
//		if (usuario.isPresent()) {
//			ingredientesRepository.deleteById(id);
//		}
//	}
}
