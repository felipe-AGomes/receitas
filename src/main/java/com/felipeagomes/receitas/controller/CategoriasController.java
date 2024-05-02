package com.felipeagomes.receitas.controller;

import java.util.List;
import java.util.Optional;

import com.felipeagomes.receitas.dtos.CategoriasDto;
import com.felipeagomes.receitas.dtos.ResponseCategoriasDto;
import com.felipeagomes.receitas.services.CategoriasService;
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
	private final CategoriasService categoriasService;

	public CategoriasController(CategoriasService categoriasService) {
		this.categoriasService = categoriasService;
	}

	@GetMapping
	public List<ResponseCategoriasDto> findAllByUsuarioId(@RequestHeader long usuarioId) {
		return categoriasService.findAllByUsuarioId(usuarioId);
	}

	@PostMapping
	public ResponseCategoriasDto saveCategoria(@RequestBody CategoriasDto categoriaDto) {
		return categoriasService.saveCategoria(categoriaDto);
	}

	@DeleteMapping
	public void deleteCategoria(@RequestHeader long id) {
		categoriasService.deleteCategoriaById(id);
	}

	@PutMapping
	public ResponseCategoriasDto updateCategoria(@RequestBody CategoriasDto categoriaDto) {
		return categoriasService.updateCategoria(categoriaDto);
	}
}
