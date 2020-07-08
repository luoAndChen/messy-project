package com.messy.log.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * log-spring-boot-starter 自动装配
 */
public class LogImportSelector implements ImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		
		return new String[] { 
				"com.messy.log.aop.LogAnnotationAOP",
				"com.messy.log.service.impl.LogServiceImpl",
				"com.messy.log.config.LogAutoConfig"
		};
	}

}