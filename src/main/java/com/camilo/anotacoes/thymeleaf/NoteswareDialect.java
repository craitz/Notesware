package com.camilo.anotacoes.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.camilo.anotacoes.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.camilo.anotacoes.thymeleaf.processor.MessageElementTagProcessor;
import com.camilo.anotacoes.thymeleaf.processor.OrderElementTagProcessor;

public class NoteswareDialect extends AbstractProcessorDialect {

	public NoteswareDialect() {
		super("Camilo Notesware", "notesware", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		return processadores;
	}

}
