package com.camilo.anotacoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camilo.anotacoes.model.Grupo;
import com.camilo.anotacoes.repository.Grupos;

@Service
public class CadastroGrupoService {
	
	@Autowired
	private Grupos grupos;
	
	@Transactional
	public void salvar(Grupo grupo) {
		grupos.save(grupo);
	}
}
