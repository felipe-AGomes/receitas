package com.felipeagomes.receitas.controller;

import com.felipeagomes.receitas.dtos.ReceitasDto;
import com.felipeagomes.receitas.dtos.ReceitasEtapasDto;
import com.felipeagomes.receitas.dtos.ResponseReceitasEtapasDto;
import com.felipeagomes.receitas.services.ReceitasEtapasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secure/receitasetapas")
public class ReceitasEtapasController {
    private final ReceitasEtapasService receitasEtapasService;

    public ReceitasEtapasController(ReceitasEtapasService receitasEtapasService) {
        this.receitasEtapasService = receitasEtapasService;
    }

    @GetMapping
    private List<ResponseReceitasEtapasDto> findAllByReceitaId(@RequestHeader long receitaId) {
        return receitasEtapasService.findAllByReceitaId(receitaId);
    }

    @PostMapping
    private ResponseReceitasEtapasDto saveReceitaEtapa(@RequestBody ReceitasEtapasDto receitasEtapasDto) {
        return receitasEtapasService.saveReceitaEtapa(receitasEtapasDto);
    }

    @DeleteMapping
    private void deleteReceitaEtapa(@RequestHeader long id) {
        receitasEtapasService.deleteReceitaEtapaById(id);
    }

    @PutMapping
    private ResponseReceitasEtapasDto updateReceitaEtapa(@RequestBody ReceitasEtapasDto receitasEtapasDto) {
        return receitasEtapasService.updateReceitaEtapa(receitasEtapasDto);
    }

}
