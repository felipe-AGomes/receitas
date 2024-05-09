package com.felipeagomes.receitas.services;

import com.felipeagomes.receitas.dtos.out.ResponseUsuariosDto;
import com.felipeagomes.receitas.dtos.in.UsuariosDto;
import com.felipeagomes.receitas.entities.Usuarios;
import com.felipeagomes.receitas.mappers.UsuariosMapper;
import com.felipeagomes.receitas.repositories.UsuariosRepository;
import com.felipeagomes.receitas.util.EntityUpdateValidator;
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

    public ResponseUsuariosDto updateUsuario(UsuariosDto usuarioDto) {
        Optional<Usuarios> optionalUsuario = usuariosRepository.findById(usuarioDto.id());

        if (optionalUsuario.isPresent()) {
            Usuarios usuario = optionalUsuario.get();
            update(usuario, usuarioDto);

            usuariosRepository.save(usuario);

            return usuariosMapper.toResponseUsuariosDto(usuario);
        }

        return null;
    }

    private void update(Usuarios oldUsuario, UsuariosDto newUsuario) {
        if (EntityUpdateValidator.canUpdate(oldUsuario.getNome(), newUsuario.nome())) {
            oldUsuario.setNome(newUsuario.nome());
        }

        if (EntityUpdateValidator.canUpdate(oldUsuario.getEmail(), newUsuario.email())) {
            oldUsuario.setEmail(newUsuario.email());
        }

        if (EntityUpdateValidator.canUpdate(oldUsuario.getSenha(), newUsuario.senha())) {
            oldUsuario.setSenha(newUsuario.senha());
        }
    }
}
