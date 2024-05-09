package com.felipeagomes.receitas.mappers;

import com.felipeagomes.receitas.dtos.in.CategoriasDto;
import com.felipeagomes.receitas.dtos.out.ResponseCategoriasDto;
import com.felipeagomes.receitas.entities.Categorias;
import org.springframework.stereotype.Service;

@Service
public class CategoriasMapper {
    public ResponseCategoriasDto toResponseCategoriasDto(Categorias categorias) {
        return new ResponseCategoriasDto(
            categorias.getId(),
            categorias.getUsuario().getId(),
            categorias.getDescricao()
        );
    }

    public Categorias toCategorias(CategoriasDto categoriaDto) {
        return new Categorias(
            categoriaDto.id(),
            categoriaDto.descricao()
        );
    }
}
