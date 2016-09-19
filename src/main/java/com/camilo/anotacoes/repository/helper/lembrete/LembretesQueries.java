package com.camilo.anotacoes.repository.helper.lembrete;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.camilo.anotacoes.model.Lembrete;
import com.camilo.anotacoes.repository.filter.LembreteFilter;

public interface LembretesQueries {

	public Page<Lembrete> filtrar(LembreteFilter filter, Pageable pageable);
}
