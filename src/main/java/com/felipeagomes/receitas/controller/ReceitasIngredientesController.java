package com.felipeagomes.receitas.controller;

import com.felipeagomes.receitas.entities.ReceitasIngredientes;
import com.felipeagomes.receitas.entities.ReceitasIngredientesId;
import com.felipeagomes.receitas.repositories.ReceitasIngredientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/secure/receitasingredientes")
public class ReceitasIngredientesController {
    @Autowired
    private ReceitasIngredientesRepository receitasIngredientesRepository;

    @GetMapping
    private ReceitasIngredientes findAll(@RequestHeader long receitaId, @RequestHeader long ingredienteId) {
        ReceitasIngredientesId receitasIngredientesId = new ReceitasIngredientesId();
        receitasIngredientesId.setReceitaId(receitaId);
        receitasIngredientesId.setIngredienteId(ingredienteId);

        Optional<ReceitasIngredientes> receitasIngredientes = receitasIngredientesRepository.findById(receitasIngredientesId);

        return receitasIngredientes.orElse(null);
    }

    private void createReceitaIngrediente() {

    }

    private void updateReceitaIngrediente() {

    }

    private void deleteReceitaIngrediente() {

    }
}
