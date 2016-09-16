package com.camilo.anotacoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camilo.anotacoes.model.Grupo;

@Repository
public interface Grupos extends JpaRepository<Grupo, Long> {

}
