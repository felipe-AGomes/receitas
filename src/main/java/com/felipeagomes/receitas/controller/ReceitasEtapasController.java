package com.felipeagomes.receitas.controller;

import com.felipeagomes.receitas.entities.Receitas;
import com.felipeagomes.receitas.entities.ReceitasEtapas;
import com.felipeagomes.receitas.entities.primarykeys.ReceitasEtapasId;
import com.felipeagomes.receitas.repositories.ReceitasEtapasRepository;
import com.felipeagomes.receitas.repositories.ReceitasRepository;
import com.felipeagomes.receitas.services.ReceitasEtapasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/secure/receitasetapas")
public class ReceitasEtapasController {
    @Autowired
    private ReceitasEtapasRepository receitasEtapasRepository;
    @Autowired
    private ReceitasRepository receitasRepository;
    @Autowired
    private ReceitasEtapasService receitasEtapasService;

    @GetMapping
    private List<ReceitasEtapas> findAll(@RequestHeader long receitaId) {
        Optional<List<ReceitasEtapas>> receitasEtapas = receitasEtapasRepository.findByIdReceitaId(receitaId);

        return receitasEtapas.orElse(null);

    }

    @PostMapping
    private ReceitasEtapas createReceitaEtapa(@RequestHeader long receitaId, @RequestBody ReceitasEtapas receitaEtapa) {
        Optional<Receitas> receita = receitasRepository.findById(receitaId);

        if (receita.isPresent()) {
            ReceitasEtapasId receitasEtapasId = new ReceitasEtapasId();
            receitasEtapasId.setEtapaId(receitasEtapasService.getNextId(receitaId));
            receitasEtapasId.setReceitaId(receitaId);
            receitaEtapa.setId(receitasEtapasId);
            receitaEtapa.setReceita(receita.get());
            receitaEtapa.setSeq(receitasEtapasService.getNextSequence(receitaId));

            return receitasEtapasRepository.save(receitaEtapa);
        }

        return null;
    }

    @PutMapping
    private ReceitasEtapas updateReceitaEtapa(@RequestHeader long id, @RequestHeader long receitaId, @RequestBody ReceitasEtapas receitaEtapa) {
        ReceitasEtapasId receitaEtapaId = new ReceitasEtapasId();
        receitaEtapaId.setReceitaId(receitaId);
        receitaEtapaId.setEtapaId(id);
        Optional<Receitas> receita = receitasRepository.findById(receitaId);

        if (receita.isEmpty()) {
            return null;
        }

        receitaEtapa.setReceita(receita.get());
        receitaEtapa.setId(receitaEtapaId);

        return receitasEtapasRepository.save(receitaEtapa);
    }

    @DeleteMapping
    private void deleteReceitaEtapa(@RequestHeader long id, @RequestHeader long receitaId) {
        ReceitasEtapasId receitasEtapasId = new ReceitasEtapasId();
        receitasEtapasId.setReceitaId(receitaId);
        receitasEtapasId.setEtapaId(id);

        Optional<ReceitasEtapas> receitasEtapas = receitasEtapasRepository.findById(receitasEtapasId);

        receitasEtapas.ifPresent(etapas -> receitasEtapasRepository.delete(etapas));
        receitasEtapasService.reorderSeq(receitaId);
    }
}
