package com.felipeagomes.receitas.services;

import com.felipeagomes.receitas.dtos.in.IngredientesDto;
import com.felipeagomes.receitas.dtos.out.ResponseIngredientesDto;
import com.felipeagomes.receitas.entities.Ingredientes;
import com.felipeagomes.receitas.entities.Usuarios;
import com.felipeagomes.receitas.mappers.IngredientesMapper;
import com.felipeagomes.receitas.repositories.IngredientesRepository;
import com.felipeagomes.receitas.repositories.UsuariosRepository;
import com.felipeagomes.receitas.util.EntityUpdateValidator;
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
        if (EntityUpdateValidator.canUpdate(oldIngrediente.getDescricao(), newIngrediente.descricao())) {
            oldIngrediente.setDescricao(newIngrediente.descricao());
        }
    }
}
