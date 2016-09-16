package com.camilo.anotacoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camilo.anotacoes.model.Lembrete;
import com.camilo.anotacoes.repository.Lembretes;

@Service
public class CadastroLembreteService {
	
	@Autowired
	private Lembretes lembretes;
	
	@Transactional
	public void salvar(Lembrete lembrete) {
		lembretes.save(lembrete);
	}
}
