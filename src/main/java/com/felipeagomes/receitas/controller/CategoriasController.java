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

import com.felipeagomes.receitas.entities.Categorias;
import com.felipeagomes.receitas.entities.Usuarios;
import com.felipeagomes.receitas.repositories.CategoriasRepository;
import com.felipeagomes.receitas.repositories.UsuariosRepository;

@RestController
@RequestMapping("/secure/categorias")
public class CategoriasController {
	@Autowired
	private CategoriasRepository categoriasRepository;
	@Autowired
	private UsuariosRepository usuariosRepository;


	@GetMapping
	public List<Categorias> findAll(@RequestHeader long usuarioId) {
		return categoriasRepository.findAllByUsuarioId(usuarioId);
	}

	@PostMapping
	public Categorias createCategoria(@RequestBody Categorias categoria, @RequestHeader long usuarioId) {
		Optional<Usuarios> usuario = usuariosRepository.findById(usuarioId);

		if (usuario.isPresent()) {
			categoria.setUsuario(usuario.get());
			return categoriasRepository.save(categoria);
		}

		return null;
	}

	@DeleteMapping
	public void deleteCategoria(@RequestHeader long id, @RequestHeader long usuarioId) {
		Optional<Usuarios> usuario = usuariosRepository.findById(usuarioId);

		if (usuario.isPresent()) {
			categoriasRepository.deleteById(id);
		}
	}

	@PutMapping
	public Categorias updateCategoria(@RequestBody Categorias categoria, @RequestHeader long id, @RequestHeader long usuarioId) {
		Optional<Usuarios> usuario = usuariosRepository.findById(usuarioId);

		if (usuario.isPresent()) {
			categoria.setUsuario(usuario.get());
			categoria.setId(id);
			categoriasRepository.save(categoria);

			return categoria;
		}

		return null;
	}
}
