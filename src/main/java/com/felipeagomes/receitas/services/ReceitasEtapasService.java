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

//    public long getNextSequence(long receitaId) {
//        Optional<List<ReceitasEtapas>> receitasEtapas = receitasEtapasRepository.findByReceitaId(receitaId);
//
//        if (receitasEtapas.isEmpty()) {
//            throw new RuntimeException("Receitas etapas não encontrada");
//        }
//
//        long nextSequence = 0;
//
//        for (ReceitasEtapas receitaEtapa : receitasEtapas.get()) {
//            nextSequence = Math.max(receitaEtapa.getSeq() + 1, nextSequence);
//        }
//
//        return nextSequence;
//    }
//
//    public void reorderSeq(long receitaId) {
//        Optional<List<ReceitasEtapas>> receitasEtapas = receitasEtapasRepository.findByReceitaId(receitaId);
//
//        if (receitasEtapas.isEmpty()) {
//            return;
//        }
//
//        for (int i = 0; i < receitasEtapas.get().size(); i++) {
//            ReceitasEtapas receitaEtapa = receitasEtapas.get().get(i);
//            if (receitaEtapa.getSeq() != i) {
//                receitaEtapa.setSeq(i);
//                receitasEtapasRepository.save(receitaEtapa);
//                receitasEtapasRepository.flush();
//            }
//        }
//    }

    public List<ResponseReceitasEtapasDto> findAllByReceitaId(long receitaId) {
        Optional<List<ReceitasEtapas>> optionalReceitasEtapas = receitasEtapasRepository.findByReceitaId(receitaId);

        return optionalReceitasEtapas.map(receitasEtapas -> receitasEtapas.stream().map(receitasEtapasMapper::toResponseReceitasEtapasDto).toList()).orElse(null);
    }

    public ResponseReceitasEtapasDto saveReceitaEtapa(ReceitasEtapasDto receitasEtapasDto) {
        Optional<Receitas> optionalReceitas = receitasRepository.findById(receitasEtapasDto.receitaId());

        if (optionalReceitas.isPresent()) {
            Receitas receita = optionalReceitas.get();
            ReceitasEtapas receitasEtapas = receitasEtapasMapper.toReceitasEtapas(receitasEtapasDto);
            receitasEtapas.setReceita(receita);

            return receitasEtapasMapper.toResponseReceitasEtapasDto(receitasEtapasRepository.save(receitasEtapas));
        }

        return null;
    }
}
