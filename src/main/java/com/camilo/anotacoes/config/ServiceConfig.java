package com.camilo.anotacoes.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.camilo.anotacoes.service.CadastroLembreteService;

@Configuration
@ComponentScan(basePackageClasses = CadastroLembreteService.class)
public class ServiceConfig {

}
