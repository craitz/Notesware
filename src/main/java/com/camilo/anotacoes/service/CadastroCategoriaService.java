package com.camilo.anotacoes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camilo.anotacoes.model.Categoria;
import com.camilo.anotacoes.repository.Categorias;
import com.camilo.anotacoes.service.exception.CategoriaJaCadastradaException;

@Service
public class CadastroCategoriaService {
	
	@Autowired
	private Categorias categorias;
	
	@Transactional
	public Categoria salvar(Categoria categoria) {
		Optional<Categoria> categoriaOptional = categorias.findByNomeIgnoreCase(categoria.getNome());
		if (categoriaOptional.isPresent()) {
			throw new CategoriaJaCadastradaException("Esta categoria já está cadastrada!");
		}

		return categorias.saveAndFlush(categoria);
	}
}
