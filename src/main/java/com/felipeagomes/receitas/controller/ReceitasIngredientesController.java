package com.felipeagomes.receitas.controller;

import com.felipeagomes.receitas.dtos.in.ReceitasIngredientesDto;
import com.felipeagomes.receitas.dtos.out.ResponseReceitasIngredientesDto;
import com.felipeagomes.receitas.services.ReceitasIngredientesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    private ResponseReceitasIngredientesDto saveReceitaIngrediente(@RequestBody ReceitasIngredientesDto receitaIngredienteDto) {
        return receitasIngredientesService.saveReceitaIngrediente(receitaIngredienteDto);
    }
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
