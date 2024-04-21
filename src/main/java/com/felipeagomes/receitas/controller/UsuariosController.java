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
@RequestMapping("/")
public class UsuariosController {
	@Autowired
	private UsuariosRepository usuariosRepository;

	@GetMapping("/secure/adm/usuarios")
	public List<Usuarios> findAll() {
		return usuariosRepository.findAll();
	}

	@PostMapping("/register")
	public Usuarios createUsuario(@RequestBody Usuarios usuario) {
		Usuarios hasUsuario = usuariosRepository.findByEmail(usuario.getEmail());

		if (hasUsuario == null) {
			return usuariosRepository.save(usuario);
		}

		return null;
	}

	@DeleteMapping("/secure/usuarios")
	public void deleteUsuario(@RequestHeader long id) {
		usuariosRepository.deleteById(id);
	}

	@PutMapping("/secure/usuarios")
	public void updateUsuario(@RequestHeader long usuarioId, @RequestBody Usuarios usuario) {
		Optional<Usuarios> user = usuariosRepository.findById(usuarioId);

		if (user.isPresent()) {
			usuario.setId(usuarioId);
			usuariosRepository.save(usuario);
		}
	}
}
