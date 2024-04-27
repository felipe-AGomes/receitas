package com.felipeagomes.receitas.services;

import com.felipeagomes.receitas.entities.ReceitasEtapas;
import com.felipeagomes.receitas.repositories.ReceitasEtapasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceitasEtapasService {
    @Autowired
    private ReceitasEtapasRepository receitasEtapasRepository;

    public long getNextSequence(long receitaId) {
        Optional<List<ReceitasEtapas>> receitasEtapas = receitasEtapasRepository.findByIdReceitaId(receitaId);

        if (receitasEtapas.isEmpty()) {
            throw new RuntimeException("Receitas etapas não encontrada");
        }

        long nextSequence = 0;

        for (ReceitasEtapas receitaEtapa : receitasEtapas.get()) {
            nextSequence = Math.max(receitaEtapa.getSeq() + 1, nextSequence);
        }

        return nextSequence;
    }

    public void reorderSeq(long receitaId) {
        Optional<List<ReceitasEtapas>> receitasEtapas = receitasEtapasRepository.findByIdReceitaId(receitaId);

        if (receitasEtapas.isEmpty()) {
            return;
        }

        for (int i = 0; i < receitasEtapas.get().size(); i++) {
            ReceitasEtapas receitaEtapa = receitasEtapas.get().get(i);
            if (receitaEtapa.getSeq() != i) {
                receitaEtapa.setSeq(i);
                receitasEtapasRepository.save(receitaEtapa);
                receitasEtapasRepository.flush();
            }
        }
    }

    public long getNextId(long receitaId) {
        Optional<List<ReceitasEtapas>> receitasEtapas = receitasEtapasRepository.findByIdReceitaId(receitaId);

        if (receitasEtapas.isEmpty()) {
            throw new RuntimeException("Receitas etapas não encontrada");
        }

        long nextSequence = 0;

        for (ReceitasEtapas receitaEtapa : receitasEtapas.get()) {
            nextSequence = Math.max(receitaEtapa.getId().getEtapaId() + 1, nextSequence);
        }

        return nextSequence;
    }
}
