package com.felipeagomes.receitas.services;

import com.felipeagomes.receitas.dtos.ReceitasDto;
import com.felipeagomes.receitas.entities.Receitas;
import com.felipeagomes.receitas.repositories.CategoriasRepository;
import com.felipeagomes.receitas.repositories.ReceitasRepository;
import com.felipeagomes.receitas.repositories.UsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceitasService {
    private final ReceitasRepository receitasRepository;
    private final UsuariosRepository usuariosRepository;
    private final CategoriasRepository categoriasRepository;
    private final ReceitasMapping receitasMapping;

    public ReceitasService(ReceitasRepository receitasRepository, UsuariosRepository usuariosRepository, CategoriasRepository categoriasRepository, ReceitasMapping receitasMapping) {
        this.receitasRepository = receitasRepository;
        this.usuariosRepository = usuariosRepository;
        this.categoriasRepository = categoriasRepository;
        this.receitasMapping = receitasMapping;
    }

    public List<ReceitasDto> findAllByUsuarioId(long usuarioId) {
        Optional<List<Receitas>> receitas = receitasRepository.findByUsuarioId(usuarioId);

        return receitas.map(receitasList -> receitasList.stream().map(receitasMapping::toReceitasDto).toList()).orElse(null);
    }

    public ReceitasDto saveReceita(ReceitasDto receitaDto) {
        return null;
    }
}
