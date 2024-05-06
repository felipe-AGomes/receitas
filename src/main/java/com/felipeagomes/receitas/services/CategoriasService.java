package com.felipeagomes.receitas.services;

import com.felipeagomes.receitas.dtos.CategoriasDto;
import com.felipeagomes.receitas.dtos.ResponseCategoriasDto;
import com.felipeagomes.receitas.entities.Categorias;
import com.felipeagomes.receitas.entities.Usuarios;
import com.felipeagomes.receitas.repositories.CategoriasRepository;
import com.felipeagomes.receitas.repositories.UsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriasService {
    private final CategoriasRepository categoriasRepository;
    private final CategoriasMapper categoriasMapper;
    private final UsuariosRepository usuariosRepository;

    public CategoriasService(CategoriasRepository categoriasRepository, CategoriasMapper categoriasMapper, UsuariosRepository usuariosRepository) {
        this.categoriasRepository = categoriasRepository;
        this.categoriasMapper = categoriasMapper;
        this.usuariosRepository = usuariosRepository;
    }

    public List<ResponseCategoriasDto> findAllByUsuarioId(long usuarioId) {
        Optional<List<Categorias>> categoriasOptional = categoriasRepository.findAllByUsuarioId(usuarioId);

        return categoriasOptional.map(categorias -> categorias.stream().map(categoriasMapper::toResponseCategoriasDto).toList()).orElse(null);
    }

    public ResponseCategoriasDto saveCategoria(CategoriasDto categoriaDto) {
        Optional<Usuarios> optionalUsuario = usuariosRepository.findById(categoriaDto.usuarioId());

        if (optionalUsuario.isPresent()) {
            Categorias categoria = categoriasMapper.toCategorias(categoriaDto);
            categoria.setUsuario(optionalUsuario.get());
            categoriasRepository.save(categoria);

            return categoriasMapper.toResponseCategoriasDto(categoria);
        }

        return null;
    }

    public void deleteCategoriaById(long id) {
        categoriasRepository.deleteById(id);
    }

    // TODO: AJUSTAR PARA UTILIZAR O METODO UPDATE E CANUPDATE
    public ResponseCategoriasDto updateCategoria(CategoriasDto categoriaDto) {
        Optional<Usuarios> optionalUsuario = usuariosRepository.findById(categoriaDto.usuarioId());
        Optional<Categorias> optionalCategoria = categoriasRepository.findById(categoriaDto.id());

        if (optionalCategoria.isPresent() && optionalUsuario.isPresent()) {
            Categorias categoria = optionalCategoria.get();

            if (!categoriaDto.descricao().equals(categoria.getDescricao())) {
                categoria.setDescricao(categoriaDto.descricao());
            }

            categoriasRepository.save(categoria);

            return categoriasMapper.toResponseCategoriasDto(categoria);
        }

        return null;
    }

//    private void update(Usuarios oldUsuario, UsuariosDto newUsuario) {
//        if (canUpdate(oldUsuario.getNome(), newUsuario.nome())) {
//            oldUsuario.setNome(newUsuario.nome());
//        }
//
//        if (canUpdate(oldUsuario.getEmail(), newUsuario.email())) {
//            oldUsuario.setEmail(newUsuario.email());
//        }
//
//        if (canUpdate(oldUsuario.getSenha(), newUsuario.senha())) {
//            oldUsuario.setSenha(newUsuario.senha());
//        }
//    }
//
//    private <T> boolean canUpdate(T oldParam, T newParam) {
//        return !oldParam.equals(newParam) && newParam != null;
//    }
}
