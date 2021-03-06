package com.camilo.anotacoes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camilo.anotacoes.model.Categoria;

@Repository
public interface Categorias extends JpaRepository<Categoria, Long> {

	public Optional<Categoria> findByNomeIgnoreCase(String nome);
}
