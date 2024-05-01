package com.felipeagomes.receitas.controller;

import java.util.List;

import com.felipeagomes.receitas.dtos.ReceitasDto;
import com.felipeagomes.receitas.dtos.ResponseReceitasDto;
import com.felipeagomes.receitas.services.ReceitasService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure/receitas")
public class ReceitasController {
	private final ReceitasService receitasService;

	public ReceitasController(ReceitasService receitasService) {
        this.receitasService = receitasService;
    }

	@GetMapping
	public List<ResponseReceitasDto> findAllByUsuarioId(@RequestHeader long usuarioId) {
		return receitasService.findAllByUsuarioId(usuarioId);
	}

	@PostMapping
	public ResponseReceitasDto saveReceita(@RequestBody ReceitasDto receitaDto) {
		return receitasService.saveReceita(receitaDto);
	}

	// TODO: Não está dando para deletar receitas que tem dependentes, ex: categorias, ingredientes...
	@DeleteMapping
	public void deleteReceita(@RequestHeader long id) {
		receitasService.deleteReceitaById(id);
	}

	@PutMapping
	public ResponseReceitasDto updateReceita(@RequestBody ReceitasDto receitaDto) {
		return receitasService.updateReceita(receitaDto);
	}

//	@GetMappingtegorias")
//	public List<Categorias> findAllCategorias(@RequestHeader long receitaId) {
//		Optional<Receitas> receita = receitasRepository.findById(receitaId);
//
//        return receita.map(Receitas::getCategorias).orElse(null);
//
//    }
//
//	@PostMappingtegorias")
//	public Categorias createReceitaCategoria(@RequestHeader long receitaId, @RequestHeader long categoriaId) {
//		Optional<Receitas> receita = receitasRepository.findById(receitaId);
//		Optional<Categorias> categoria = categoriasRepository.findById(categoriaId);
//
//		if (receita.isPresent() && categoria.isPresent()) {
//			receita.get().addCategoria(categoria.get());
//			receitasRepository.save(receita.get());
//
//			return categoria.get();
//		}
//
//		return null;
//	}
//
//	@PutMappingtegorias")
//	public void updateReceitaCategoria(@RequestHeader long receitaId, @RequestHeader long categoriaId, @RequestBody Categorias categorias) {
//		Optional<Receitas> receita = receitasRepository.findById(receitaId);
//		Optional<Categorias> categoria = categoriasRepository.findById(categoriaId);
//
//		if (receita.isPresent() && categoria.isPresent()) {
//			receita.get().deleteCategoria(categoria.get());
//
//			receita.get().addCategoria(categorias);
//			receitasRepository.save(receita.get());
//		}
//	}
//
//	@DeleteMappingtegorias")
//	public void deleteReceitaCategoria(@RequestHeader long receitaId, @RequestHeader long categoriaId) {
//		Optional<Receitas> receita = receitasRepository.findById(receitaId);
//		Optional<Categorias> categoria = categoriasRepository.findById(categoriaId);
//
//		if (receita.isPresent() && categoria.isPresent()) {
//			receita.get().deleteCategoria(categoria.get());
//			receitasRepository.save(receita.get());
//		}
//	}
}
