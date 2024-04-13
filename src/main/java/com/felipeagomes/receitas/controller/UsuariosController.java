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

import com.felipeagomes.receitas.entities.Usuarios;
import com.felipeagomes.receitas.repositories.UsuariosRepository;

@RestController
@RequestMapping(value = "/")
public class UsuariosController {
	@Autowired
	private UsuariosRepository repository;

	@GetMapping(value = "/secure/adm/usuarios")
	public List<Usuarios> findAll() {
		return repository.findAll();
	}

	@PostMapping(value = "/register")
	public Usuarios createUsuario(@RequestBody Usuarios usuario) {
		return repository.save(usuario);
	}

	@DeleteMapping(value = "/secure/usuarios")
	public void deleteUsuario(@RequestHeader Long id) {
		repository.deleteById(id);
	}

	@PutMapping(value = "/secure/usuarios")
	public void updateUsuario(@RequestBody Usuarios usuario) {
		Optional<Usuarios> user = repository.findById(usuario.getId());
		
		if (user.isPresent()) {
			repository.save(usuario);
		}
	}
}
