package com.camilo.anotacoes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camilo.anotacoes.model.Grupo;
import com.camilo.anotacoes.repository.Grupos;
import com.camilo.anotacoes.service.exception.GrupoJaCadastradoException;

@Service
public class CadastroGrupoService {
	
	@Autowired
	private Grupos grupos;
	
	@Transactional
	public Grupo salvar(Grupo grupo) {
		Optional<Grupo> grupoOptional = grupos.findByNomeIgnoreCase(grupo.getNome());
		if (grupoOptional.isPresent()) {
			throw new GrupoJaCadastradoException("Este grupo já está cadastrado!");
		}
		
		return grupos.saveAndFlush(grupo);
	}
}
