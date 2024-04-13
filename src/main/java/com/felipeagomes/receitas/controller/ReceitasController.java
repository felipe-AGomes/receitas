package com.felipeagomes.receitas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipeagomes.receitas.entities.Receitas;
import com.felipeagomes.receitas.repositories.ReceitasRepository;

@RestController
@RequestMapping(value = "/secure/receitas")
public class ReceitasController {
	@Autowired
	private ReceitasRepository controller;

	@GetMapping
	public List<Receitas> findAll(@RequestHeader long usuarioId) {
		return controller.findByUsuarioId(usuarioId);
	}

	@PostMapping
	public Receitas createReceita(@RequestBody Receitas receita) {
		return controller.save(receita);
	}

	@DeleteMapping
	public void deleteReceita(@RequestHeader Long id) {
		controller.deleteById(id);
	}
}
