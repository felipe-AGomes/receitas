package com.felipeagomes.receitas.services;

import com.felipeagomes.receitas.dtos.ReceitasDto;
import com.felipeagomes.receitas.dtos.ResponseReceitasDto;
import com.felipeagomes.receitas.entities.Receitas;
import com.felipeagomes.receitas.entities.Usuarios;
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
    private final ReceitasMapper receitasMapper;

    public ReceitasService(ReceitasRepository receitasRepository, UsuariosRepository usuariosRepository, CategoriasRepository categoriasRepository, ReceitasMapper receitasMapper) {
        this.receitasRepository = receitasRepository;
        this.usuariosRepository = usuariosRepository;
        this.categoriasRepository = categoriasRepository;
        this.receitasMapper = receitasMapper;
    }

    public List<ResponseReceitasDto> findAllByUsuarioId(long usuarioId) {
        Optional<List<Receitas>> receitas = receitasRepository.findByUsuarioId(usuarioId);

        return receitas.map(receitasList -> receitasList.stream().map(receitasMapper::toResponseReceitasDto).toList()).orElse(null);
    }

    public ResponseReceitasDto saveReceita(ReceitasDto receitaDto) {
        Optional<Usuarios> usuario = usuariosRepository.findById(receitaDto.usuarioId());

        if (usuario.isPresent()) {
            Receitas receita = receitasMapper.toReceitas(receitaDto);
            receita.setUsuario(usuario.get());
            receitasRepository.save(receita);

            return receitasMapper.toResponseReceitasDto(receita);
        }

        return null;
    }

    public void deleteReceitaById(long id) {
        receitasRepository.deleteById(id);
    }

    public ResponseReceitasDto updateReceita(ReceitasDto receitaDto) {
        Optional<Receitas> optionalReceita = receitasRepository.findById(receitaDto.id());
        Optional<Usuarios> optionalUsuario = usuariosRepository.findById(receitaDto.usuarioId());

        if (optionalReceita.isPresent() && optionalUsuario.isPresent()){
            Receitas receita = optionalReceita.get();
            if (!receitaDto.descricao().equals(receita.getDescricao())) {
                receita.setDescricao(receitaDto.descricao());
            }
            if (!receitaDto.obsLivre().equals(receita.getObsLivre())) {
                receita.setObsLivre(receitaDto.obsLivre());
            }
            if (receitaDto.preparacaoMinuto() != receita.getPreparacaoMinuto()) {
                receita.setPreparacaoMinuto(receitaDto.preparacaoMinuto());
            }

            receitasRepository.save(receita);

            return receitasMapper.toResponseReceitasDto(receita);
        }
        return null;
    }
}
