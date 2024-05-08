package com.felipeagomes.receitas.services;

import com.felipeagomes.receitas.dtos.ReceitasEtapasDto;
import com.felipeagomes.receitas.dtos.ResponseReceitasEtapasDto;
import com.felipeagomes.receitas.entities.Receitas;
import com.felipeagomes.receitas.entities.ReceitasEtapas;
import com.felipeagomes.receitas.exceptions.InvalidSequenceException;
import com.felipeagomes.receitas.repositories.ReceitasEtapasRepository;
import com.felipeagomes.receitas.repositories.ReceitasRepository;
import com.felipeagomes.receitas.repositories.UsuariosRepository;
import com.felipeagomes.receitas.util.EntityUpdateValidator;
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
        Optional<List<ReceitasEtapas>> optionalReceitasEtapas = receitasEtapasRepository.findByReceitaIdOrderBySeq(receitaId);

        return optionalReceitasEtapas.map(receitasEtapas -> receitasEtapas.stream().map(receitasEtapasMapper::toResponseReceitasEtapasDto).toList()).orElse(null);
    }

    public ResponseReceitasEtapasDto saveReceitaEtapa(ReceitasEtapasDto receitasEtapasDto) {
        validateSeq(receitasEtapasDto.seq());

        Optional<Receitas> optionalReceita = receitasRepository.findById(receitasEtapasDto.receitaId());

        if (optionalReceita.isPresent()) {
            Receitas receita = optionalReceita.get();
            long seq = receitasEtapasDto.seq();
            adjustSequenceForInsert(receita, seq);

            ReceitasEtapas receitasEtapas = receitasEtapasMapper.toReceitasEtapas(receitasEtapasDto);
            receitasEtapas.setReceita(receita);
            receitasEtapasRepository.save(receitasEtapas);

            reorderSeq(receita.getId());

            return receitasEtapasMapper.toResponseReceitasEtapasDto(receitasEtapas);
        }

        return null;
    }

    public void deleteReceitaEtapaById(long id) {
        Optional<ReceitasEtapas> optionalReceitasEtapas = receitasEtapasRepository.findByIdOrderBySeq(id);

        optionalReceitasEtapas.ifPresent(etapa -> {
            Receitas receita = etapa.getReceita();
            receitasEtapasRepository.deleteById(id);
            reorderSeq(receita.getId());
        });
    }

    public ResponseReceitasEtapasDto updateReceitaEtapa(ReceitasEtapasDto receitasEtapasDto) {
        validateSeq(receitasEtapasDto.seq());

        Optional<Receitas> optionalReceita = receitasRepository.findById(receitasEtapasDto.receitaId());
        Optional<ReceitasEtapas> optionalReceitasEtapas = receitasEtapasRepository.findById(receitasEtapasDto.id());

        if (optionalReceita.isPresent() && optionalReceitasEtapas.isPresent()) {
            Receitas receita = optionalReceita.get();
            ReceitasEtapas receitaEtapa = optionalReceitasEtapas.get();

            long seq = receitasEtapasDto.seq();
            if (receitaEtapa.getSeq() != seq) {
                adjustSequenceForInsert(receita, seq);
            }

            update(receitaEtapa, receitasEtapasDto);
            receitasEtapasRepository.save(receitaEtapa);

            reorderSeq(receita.getId());

            return receitasEtapasMapper.toResponseReceitasEtapasDto(receitaEtapa);
        }

        return null;
    }

    private void update(ReceitasEtapas oldReceitasEtapas, ReceitasEtapasDto newReceitasEtapas) {
        if (EntityUpdateValidator.canUpdate(oldReceitasEtapas.getDescricao(), newReceitasEtapas.descricao())) {
            oldReceitasEtapas.setDescricao(newReceitasEtapas.descricao());
        }

        if (EntityUpdateValidator.canUpdate(oldReceitasEtapas.getSeq(), newReceitasEtapas.seq()) && newReceitasEtapas.seq() != 0) {
            oldReceitasEtapas.setSeq(newReceitasEtapas.seq());
        }
    }

    private void validateSeq(long seq) {
        if (seq == 0) {
            throw new InvalidSequenceException("Sequencia deve ser maior do que 0");
        }
    }

    private void adjustSequenceForInsert(Receitas receita, long positionToInsert) {
        List<ReceitasEtapas> receitasEtapas = receita.getEtapas();

        for (ReceitasEtapas etapa : receitasEtapas) {
            if (etapa.getSeq() >= positionToInsert) {
                etapa.setSeq(etapa.getSeq() + 1);
            }
        }

        receitasEtapasRepository.saveAll(receitasEtapas);
    }

    private void reorderSeq(long receitaId) {
        receitasEtapasRepository.findByReceitaIdOrderBySeq(receitaId).ifPresent(receitasEtapas -> {
            for (int i = 0; i < receitasEtapas.size(); i++) {
                ReceitasEtapas etapa = receitasEtapas.get(i);
                etapa.setSeq(i + 1);
            }
            receitasEtapasRepository.saveAll(receitasEtapas);
        });
    }
}
