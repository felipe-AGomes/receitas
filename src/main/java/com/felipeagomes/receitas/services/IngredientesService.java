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
}
