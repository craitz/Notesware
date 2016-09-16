package com.camilo.anotacoes.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.camilo.anotacoes.model.Categoria;

public class CategoriaConverter implements Converter<String, Categoria> {

	@Override
	public Categoria convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Categoria categoria = new Categoria();
			categoria.setCodigo(Long.valueOf(codigo));
			return categoria;
		}
		
		return null;
	}

}
