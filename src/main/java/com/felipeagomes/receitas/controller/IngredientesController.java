package com.felipeagomes.receitas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipeagomes.receitas.entities.Ingredientes;
import com.felipeagomes.receitas.repositories.IngredientesRepository;

@RestController
@RequestMapping("/secure/ingredientes")
public class IngredientesController {
	@Autowired
	private IngredientesRepository repository;
	
	@GetMapping
	private List<Ingredientes> findAll(@RequestHeader long usuarioId) {
		return repository.findAllByUsuarioId(usuarioId);
	}
}
