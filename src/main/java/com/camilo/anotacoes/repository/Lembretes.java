package com.camilo.anotacoes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camilo.anotacoes.model.Lembrete;

@Repository
public interface Lembretes extends JpaRepository<Lembrete, Long> {
	
	public Optional<Lembrete> findByTituloIgnoreCase( String titulo);
}
