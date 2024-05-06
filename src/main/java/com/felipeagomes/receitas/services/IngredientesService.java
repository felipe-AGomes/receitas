package com.felipeagomes.receitas.services;

import com.felipeagomes.receitas.dtos.IngredientesDto;
import com.felipeagomes.receitas.dtos.ResponseIngredientesDto;
import com.felipeagomes.receitas.entities.Ingredientes;
import com.felipeagomes.receitas.entities.Usuarios;
import com.felipeagomes.receitas.repositories.IngredientesRepository;
import com.felipeagomes.receitas.repositories.UsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientesService {
    private final IngredientesRepository ingredientesRepository;
    private final UsuariosRepository usuariosRepository;
    private final IngredientesMapper ingredientesMapper;

    public IngredientesService(IngredientesRepository ingredientesRepository, UsuariosRepository usuariosRepository, IngredientesMapper ingredientesMapper) {
        this.ingredientesRepository = ingredientesRepository;
        this.usuariosRepository = usuariosRepository;
        this.ingredientesMapper = ingredientesMapper;
    }

    public List<ResponseIngredientesDto> findAllByUsuarioId(long usuarioId) {
        Optional<List<Ingredientes>> optionalIngredientes = ingredientesRepository.findAllByUsuarioId(usuarioId);

        return optionalIngredientes.map((optionalIngreciente) -> optionalIngreciente.stream().map(ingredientesMapper::toResponseIngredientesDto).toList()).orElse(null);
    }

    public ResponseIngredientesDto saveIngrediente(IngredientesDto ingredientesDto) {
        Optional<Usuarios> optionalUsuario = usuariosRepository.findById(ingredientesDto.usuarioId());

        if (optionalUsuario.isPresent()) {
            Ingredientes ingrediente = ingredientesMapper.toIngredientes(ingredientesDto);
            ingrediente.setUsuario(optionalUsuario.get());
            ingredientesRepository.save(ingrediente);

            return ingredientesMapper.toResponseIngredientesDto(ingrediente);
        }

        return null;
    }

    public void deleteIngredienteById(long id) {
        ingredientesRepository.deleteById(id);
    }

    // TODO: AJUSTAR PARA UTILIZAR O METODO UPDATE E CANUPDATE
    public ResponseIngredientesDto updateIngrediente(IngredientesDto ingredienteDto) {
        Optional<Usuarios> optionalUsuario = usuariosRepository.findById(ingredienteDto.usuarioId());
        Optional<Ingredientes> optionalIngrediente = ingredientesRepository.findById(ingredienteDto.usuarioId());

        if (optionalUsuario.isPresent() && optionalIngrediente.isPresent()) {
            Ingredientes ingrediente = optionalIngrediente.get();
            update(ingrediente, ingredienteDto);
            ingredientesRepository.save(ingrediente);

            return ingredientesMapper.toResponseIngredientesDto(ingrediente);
        }

        return null;
    }

    private void update(Ingredientes oldIngrediente, IngredientesDto newIngrediente) {
        if (!newIngrediente.descricao().equals(oldIngrediente.getDescricao())) {
            oldIngrediente.setDescricao(newIngrediente.descricao());
        }
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
