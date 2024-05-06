package com.felipeagomes.receitas.services;

import com.felipeagomes.receitas.dtos.ReceitasEtapasDto;
import com.felipeagomes.receitas.dtos.ResponseReceitasEtapasDto;
import com.felipeagomes.receitas.entities.Receitas;
import com.felipeagomes.receitas.entities.ReceitasEtapas;
import com.felipeagomes.receitas.entities.Usuarios;
import com.felipeagomes.receitas.repositories.ReceitasEtapasRepository;
import com.felipeagomes.receitas.repositories.ReceitasRepository;
import com.felipeagomes.receitas.repositories.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceitasEtapasService {
    private final ReceitasEtapasRepository receitasEtapasRepository;
    private final ReceitasRepository receitasRepository;
    private final UsuariosRepository usuariosRepository;
    private final ReceitasEtapasMapper receitasEtapasMapper;

    public ReceitasEtapasService(ReceitasEtapasRepository receitasEtapasRepository, ReceitasRepository receitasRepository, UsuariosRepository usuariosRepository, ReceitasEtapasMapper receitasEtapasMapper) {
        this.receitasEtapasRepository = receitasEtapasRepository;
        this.receitasRepository = receitasRepository;
        this.usuariosRepository = usuariosRepository;
        this.receitasEtapasMapper = receitasEtapasMapper;
    }

    public List<ResponseReceitasEtapasDto> findAllByReceitaId(long receitaId) {
        Optional<List<ReceitasEtapas>> optionalReceitasEtapas = receitasEtapasRepository.findByReceitaId(receitaId);

        return optionalReceitasEtapas.map(receitasEtapas -> receitasEtapas.stream().map(receitasEtapasMapper::toResponseReceitasEtapasDto).toList()).orElse(null);
    }

    public ResponseReceitasEtapasDto saveReceitaEtapa(ReceitasEtapasDto receitasEtapasDto) {
        Optional<Receitas> optionalReceitas = receitasRepository.findById(receitasEtapasDto.receitaId());

        if (optionalReceitas.isPresent()) {
            Receitas receita = optionalReceitas.get();

            if (isSeqAlreadyUsed(receita, receitasEtapasDto.seq())) {
                adjustSequence(receita, receitasEtapasDto.seq());
            }

            ReceitasEtapas receitasEtapas = receitasEtapasMapper.toReceitasEtapas(receitasEtapasDto);
            receitasEtapas.setReceita(receita);

            return receitasEtapasMapper.toResponseReceitasEtapasDto(receitasEtapasRepository.save(receitasEtapas));
        }

        return null;
    }

    private boolean isSeqAlreadyUsed(Receitas receita, long seq) {
        return receita.getEtapas().stream().filter((etapa) -> etapa.getSeq() == seq).toList().size() == 1;
    }

    private void adjustSequence(Receitas receita, long seq) {
        List<ReceitasEtapas> receitasEtapas = receita.getEtapas();

        for (int i = receitasEtapas.size() - 1; i > 0; i--) {
            ReceitasEtapas etapa = receitasEtapas.get(i);
            if (etapa.getSeq() >= seq) {
                etapa.setSeq(etapa.getSeq() + 1);
                receitasEtapasRepository.save(etapa);
            }
        }
    }
}
