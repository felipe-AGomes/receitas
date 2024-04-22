/*
package com.felipeagomes.receitas.controller;

import com.felipeagomes.receitas.entities.ReceitasEtapas;
import com.felipeagomes.receitas.repositories.ReceitasEtapasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReceitasEtapasController {
    @Autowired
    private ReceitasEtapasRepository receitasEtapasRepository;

    @GetMapping
    private List<ReceitasEtapas> listAll(@RequestHeader long usuarioId) {
        return receitasEtapasRepository.getByUsuarioId(usuarioId);
    }
}
*/
