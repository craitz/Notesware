package com.camilo.anotacoes.config.init;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.camilo.anotacoes.config.JPAConfig;
import com.camilo.anotacoes.config.ServiceConfig;
import com.camilo.anotacoes.config.WebConfigClass;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] { JPAConfig.class, ServiceConfig.class };	
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// Classe que vai ensinar o Spring a encontrar os controllers
		return new Class<?>[] { WebConfigClass.class };
	}

	@Override
	protected String[] getServletMappings() {
		// Padrão da url que será delegado para o DispatcherServlet
		return new String[] {"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		//filtro novo para forçar o encoding UTF-8
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		
		return new Filter[] { characterEncodingFilter };
	}
	
}
