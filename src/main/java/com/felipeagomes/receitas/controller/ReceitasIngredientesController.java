package com.felipeagomes.receitas.controller;

import com.felipeagomes.receitas.entities.Ingredientes;
import com.felipeagomes.receitas.entities.Receitas;
import com.felipeagomes.receitas.entities.ReceitasIngredientes;
import com.felipeagomes.receitas.entities.ReceitasIngredientesId;
import com.felipeagomes.receitas.repositories.IngredientesRepository;
import com.felipeagomes.receitas.repositories.ReceitasIngredientesRepository;
import com.felipeagomes.receitas.repositories.ReceitasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/secure/receitasingredientes")
public class ReceitasIngredientesController {
    @Autowired
    private ReceitasIngredientesRepository receitasIngredientesRepository;
    @Autowired
    private ReceitasRepository receitasRepository;
    @Autowired
    private IngredientesRepository ingredientesRepository;

    @GetMapping
    private List<ReceitasIngredientes> findAll(@RequestHeader long receitaId) {
        return receitasIngredientesRepository.findByReceitaId(receitaId);
    }

    @PostMapping
    private ReceitasIngredientes createReceitaIngrediente(@RequestHeader long receitaId, @RequestHeader long ingredienteId, @RequestBody ReceitasIngredientes receitasIngredientes) {
        Optional<Receitas> receita = receitasRepository.findById(receitaId);
        Optional<Ingredientes> ingrediente = ingredientesRepository.findById(ingredienteId);

        if (receita.isPresent() && ingrediente.isPresent()) {
            ReceitasIngredientesId receitasIngredientesId = new ReceitasIngredientesId();
            receitasIngredientesId.setIngredienteId(ingredienteId);
            receitasIngredientesId.setReceitaId(receitaId);

            receitasIngredientes.setId(receitasIngredientesId);
            receitasIngredientes.setReceita(receita.get());
            receitasIngredientes.setIngrediente(ingrediente.get());
            return receitasIngredientesRepository.save(receitasIngredientes);
        }

        return null;
    }

    @PutMapping
    private ReceitasIngredientes updateReceitaIngrediente(@RequestHeader long receitaId, @RequestHeader long ingredienteId, @RequestBody ReceitasIngredientes receitasIngredientes) {
        Optional<Receitas> receita = receitasRepository.findById(receitaId);
        Optional<Ingredientes> ingrediente = ingredientesRepository.findById(ingredienteId);

        if (receita.isPresent() && ingrediente.isPresent()) {
            ReceitasIngredientesId receitasIngredientesId = new ReceitasIngredientesId();
            receitasIngredientesId.setIngredienteId(ingredienteId);
            receitasIngredientesId.setReceitaId(receitaId);

            receitasIngredientes.setId(receitasIngredientesId);
            receitasIngredientes.setReceita(receita.get());
            receitasIngredientes.setIngrediente(ingrediente.get());
            return receitasIngredientesRepository.save(receitasIngredientes);
        }

        return null;

    }

    @DeleteMapping
    private void deleteReceitaIngrediente(@RequestHeader long receitaId, @RequestHeader long ingredienteId) {
        Optional<Receitas> receita = receitasRepository.findById(receitaId);
        Optional<Ingredientes> ingrediente = ingredientesRepository.findById(ingredienteId);

        if (receita.isPresent() && ingrediente.isPresent()) {
            ReceitasIngredientesId receitasIngredientesId = new ReceitasIngredientesId();
            receitasIngredientesId.setIngredienteId(ingredienteId);
            receitasIngredientesId.setReceitaId(receitaId);

            receitasIngredientesRepository.deleteById(receitasIngredientesId);
        }
    }
}
