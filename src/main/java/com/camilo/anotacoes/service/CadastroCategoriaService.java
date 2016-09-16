package com.camilo.anotacoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camilo.anotacoes.model.Categoria;
import com.camilo.anotacoes.repository.Categorias;

@Service
public class CadastroCategoriaService {
	
	@Autowired
	private Categorias categorias;
	
	@Transactional
	public void salvar(Categoria categoria) {
		categorias.save(categoria);
	}
}
