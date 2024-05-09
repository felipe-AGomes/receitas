package com.felipeagomes.receitas.controller;

import com.felipeagomes.receitas.dtos.ResponseReceitasIngredientesDto;
import com.felipeagomes.receitas.entities.Ingredientes;
import com.felipeagomes.receitas.entities.Receitas;
import com.felipeagomes.receitas.entities.ReceitasIngredientes;
import com.felipeagomes.receitas.entities.primarykeys.ReceitasIngredientesId;
import com.felipeagomes.receitas.repositories.IngredientesRepository;
import com.felipeagomes.receitas.repositories.ReceitasIngredientesRepository;
import com.felipeagomes.receitas.repositories.ReceitasRepository;
import com.felipeagomes.receitas.services.ReceitasIngredientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/secure/receitasingredientes")
public class ReceitasIngredientesController {
    private final ReceitasIngredientesService receitasIngredientesService;

    public ReceitasIngredientesController(ReceitasIngredientesService receitasIngredientesService) {
        this.receitasIngredientesService = receitasIngredientesService;
    }

    @GetMapping
    private List<ResponseReceitasIngredientesDto> findAllByReceitaId(@RequestHeader long receitaId) {
        return receitasIngredientesService.findAllByReceitaId(receitaId);
    }

//    @PostMapping
//    private ReceitasIngredientes createReceitaIngrediente(@RequestHeader long receitaId, @RequestHeader long ingredienteId, @RequestBody ReceitasIngredientes receitasIngredientes) {
//        Optional<Receitas> receita = receitasRepository.findById(receitaId);
//        Optional<Ingredientes> ingrediente = ingredientesRepository.findById(ingredienteId);
//
//        if (receita.isPresent() && ingrediente.isPresent()) {
//            ReceitasIngredientesId receitasIngredientesId = new ReceitasIngredientesId();
//            receitasIngredientesId.setIngredienteId(ingredienteId);
//            receitasIngredientesId.setReceitaId(receitaId);
//
//            receitasIngredientes.setId(receitasIngredientesId);
//            receitasIngredientes.setReceita(receita.get());
//            receitasIngredientes.setIngrediente(ingrediente.get());
//            return receitasIngredientesRepository.save(receitasIngredientes);
//        }
//
//        return null;
//    }
//
//    @PutMapping
//    private ReceitasIngredientes updateReceitaIngrediente(@RequestHeader long receitaId, @RequestHeader long ingredienteId, @RequestBody ReceitasIngredientes receitasIngredientes) {
//        Optional<Receitas> receita = receitasRepository.findById(receitaId);
//        Optional<Ingredientes> ingrediente = ingredientesRepository.findById(ingredienteId);
//
//        if (receita.isPresent() && ingrediente.isPresent()) {
//            ReceitasIngredientesId receitasIngredientesId = new ReceitasIngredientesId();
//            receitasIngredientesId.setIngredienteId(ingredienteId);
//            receitasIngredientesId.setReceitaId(receitaId);
//
//            receitasIngredientes.setId(receitasIngredientesId);
//            receitasIngredientes.setReceita(receita.get());
//            receitasIngredientes.setIngrediente(ingrediente.get());
//            return receitasIngredientesRepository.save(receitasIngredientes);
//        }
//
//        return null;
//
//    }
//
//    @DeleteMapping
//    private void deleteReceitaIngrediente(@RequestHeader long receitaId, @RequestHeader long ingredienteId) {
//        Optional<Receitas> receita = receitasRepository.findById(receitaId);
//        Optional<Ingredientes> ingrediente = ingredientesRepository.findById(ingredienteId);
//
//        if (receita.isPresent() && ingrediente.isPresent()) {
//            ReceitasIngredientesId receitasIngredientesId = new ReceitasIngredientesId();
//            receitasIngredientesId.setIngredienteId(ingredienteId);
//            receitasIngredientesId.setReceitaId(receitaId);
//
//            receitasIngredientesRepository.deleteById(receitasIngredientesId);
//        }
//    }
}
