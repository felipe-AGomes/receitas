package com.felipeagomes.receitas.controller;

import java.util.List;
import java.util.Optional;

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
@RequestMapping("/secure/receitas")
public class ReceitasController {
	@Autowired
	private ReceitasRepository receitasRepository;
	@Autowired
	private UsuariosRepository usuariosRepository;

	@GetMapping
	public List<Receitas> findAll(@RequestHeader long usuarioId) {
		return receitasRepository.findByUsuarioId(usuarioId);
	}

	@PostMapping
	public Receitas createReceita(@RequestBody Receitas receita, @RequestHeader long usuarioId) {
		Optional<Usuarios> usuario = usuariosRepository.findById(usuarioId);

		if (usuario.isPresent()) {
			receita.setUsuario(usuario.get());
			receitasRepository.save(receita);
		}

		return receita;
	}

	@PutMapping
	public Receitas updateReceita(@RequestBody Receitas receita, @RequestHeader long id, @RequestHeader long usuarioId) {
		Optional<Usuarios> usuario = usuariosRepository.findById(usuarioId);

		if (usuario.isPresent()) {
			receita.setUsuario(usuario.get());
			receita.setId(id);
			return receitasRepository.save(receita);
		}

		return null;
	}

	@DeleteMapping
	public void deleteReceita(@RequestHeader long id, @RequestHeader long usuarioId) {
		Optional<Usuarios> usuario = usuariosRepository.findById(usuarioId);

		if (usuario.isPresent()) {
			receitasRepository.deleteById(id);
		}
	}
}
