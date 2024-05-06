package com.felipeagomes.receitas.controller;

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
//
//    @PutMapping
//    private ReceitasEtapas updateReceitaEtapa(@RequestHeader long id, @RequestHeader long receitaId, @RequestBody ReceitasEtapas receitaEtapa) {
//        ReceitasEtapasId receitaEtapaId = new ReceitasEtapasId();
//        receitaEtapaId.setReceitaId(receitaId);
//        receitaEtapaId.setEtapaId(id);
//        Optional<Receitas> receita = receitasRepository.findById(receitaId);
//
//        if (receita.isEmpty()) {
//            return null;
//        }
//
//        receitaEtapa.setReceita(receita.get());
//        receitaEtapa.setId(receitaEtapaId);
//
//        return receitasEtapasRepository.save(receitaEtapa);
//    }
//
//    @DeleteMapping
//    private void deleteReceitaEtapa(@RequestHeader long id, @RequestHeader long receitaId) {
//        ReceitasEtapasId receitasEtapasId = new ReceitasEtapasId();
//        receitasEtapasId.setReceitaId(receitaId);
//        receitasEtapasId.setEtapaId(id);
//
//        Optional<ReceitasEtapas> receitasEtapas = receitasEtapasRepository.findById(receitasEtapasId);
//
//        receitasEtapas.ifPresent(etapas -> receitasEtapasRepository.delete(etapas));
//        receitasEtapasService.reorderSeq(receitaId);
//    }
}
