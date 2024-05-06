package com.felipeagomes.receitas.services;

import com.felipeagomes.receitas.dtos.ResponseUsuariosDto;
import com.felipeagomes.receitas.dtos.UsuariosDto;
import com.felipeagomes.receitas.entities.Usuarios;
import org.springframework.stereotype.Service;

@Service
public class UsuariosMapper {
    public ResponseUsuariosDto toResponseUsuariosDto(Usuarios usuarios) {
        return new ResponseUsuariosDto(
                usuarios.getId(),
                usuarios.getNome(),
                usuarios.getEmail()
        );
    }

    public Usuarios toUsuarios(UsuariosDto usuarioDto) {
        return new Usuarios(
                usuarioDto.id(),
                usuarioDto.nome(),
                usuarioDto.email(),
                usuarioDto.senha()
        );
    }
}
