package com.camilo.anotacoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camilo.anotacoes.model.Lembrete;
import com.camilo.anotacoes.repository.helper.lembrete.LembretesQueries;

@Repository
public interface Lembretes extends JpaRepository<Lembrete, Long>, LembretesQueries {

}
