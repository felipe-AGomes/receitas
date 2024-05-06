package com.felipeagomes.receitas.controller;

import java.util.List;
import java.util.Optional;

import com.felipeagomes.receitas.dtos.ResponseUsuariosDto;
import com.felipeagomes.receitas.dtos.UsuariosDto;
import com.felipeagomes.receitas.services.UsuariosService;
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
@RequestMapping
public class UsuariosController {
	private final UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping("/secure/adm/usuarios")
	public List<ResponseUsuariosDto> findAll() {
		return usuariosService.findAll();
	}

	@GetMapping("/secure/usuarios")
	public ResponseUsuariosDto findByUsuarioId(@RequestHeader long id) {
		return usuariosService.findByUsuarioId(id);
	}

	@PostMapping("/register")
	public ResponseUsuariosDto saveUsuario(@RequestBody UsuariosDto usuarioDto) {
		return usuariosService.saveUsuario(usuarioDto);
	}

	@DeleteMapping("/secure/usuarios")
	public void deleteUsuario(@RequestHeader long id) {
		usuariosService.deleteUsuarioById(id);
	}
//
//	@PutMapping("/secure/usuarios")
//	public void updateUsuario(@RequestHeader long usuarioId, @RequestBody Usuarios usuario) {
//		Optional<Usuarios> user = usuariosRepository.findById(usuarioId);
//
//		if (user.isPresent()) {
//			usuario.setId(usuarioId);
//			usuariosRepository.save(usuario);
//		}
//	}
}
