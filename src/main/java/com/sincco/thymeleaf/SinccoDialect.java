package com.sincco.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.sincco.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.sincco.thymeleaf.processor.MenuAttributeTagProcessor;
import com.sincco.thymeleaf.processor.MessageElementTagProcessor;
import com.sincco.thymeleaf.processor.OrderElementTagProcessor;

public class SinccoDialect extends AbstractProcessorDialect {

	public SinccoDialect() {
		super("Sincco", "sincco", StandardDialect.PROCESSOR_PRECEDENCE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores =  new  HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new MenuAttributeTagProcessor(dialectPrefix));
		return processadores;
	}

}
