package com.felipeagomes.receitas.services;

import com.felipeagomes.receitas.dtos.ResponseUsuariosDto;
import com.felipeagomes.receitas.dtos.UsuariosDto;
import com.felipeagomes.receitas.entities.Usuarios;
import com.felipeagomes.receitas.repositories.UsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService {
    private final UsuariosRepository usuariosRepository;
    private final UsuariosMapper usuariosMapper;

    public UsuariosService(UsuariosRepository usuariosRepository, UsuariosMapper usuariosMapper) {
        this.usuariosRepository = usuariosRepository;
        this.usuariosMapper = usuariosMapper;
    }

    public List<ResponseUsuariosDto> findAll() {
        return usuariosRepository.findAll().stream().map(usuariosMapper::toResponseUsuariosDto).toList();
    }

    public ResponseUsuariosDto findByUsuarioId(long id) {
        Optional<Usuarios> optionalUsuario = usuariosRepository.findById(id);

        return optionalUsuario.map(usuariosMapper::toResponseUsuariosDto).orElse(null);
    }

    public ResponseUsuariosDto saveUsuario(UsuariosDto usuarioDto) {
        Usuarios usuario = usuariosRepository.save(usuariosMapper.toUsuarios(usuarioDto));

        return usuariosMapper.toResponseUsuariosDto(usuario);
    }

    public void deleteUsuarioById(long id) {
        usuariosRepository.deleteById(id);
    }
}
