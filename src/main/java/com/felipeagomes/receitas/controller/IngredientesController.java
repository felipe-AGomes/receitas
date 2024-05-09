package com.felipeagomes.receitas.controller;

import java.util.List;

import com.felipeagomes.receitas.dtos.in.IngredientesDto;
import com.felipeagomes.receitas.dtos.out.ResponseIngredientesDto;
import com.felipeagomes.receitas.services.IngredientesService;
import org.springframework.web.bind.annotation.*;

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

	@DeleteMapping
	private void deleteIngrediente(@RequestHeader long id) {
		ingredientesService.deleteIngredienteById(id);
	}

	@PutMapping
	private ResponseIngredientesDto updateIngrediente(@RequestBody IngredientesDto ingredienteDto) {
		return ingredientesService.updateIngrediente(ingredienteDto);
	}

}
