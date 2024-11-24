package com.srltas.runtogether.common.log;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.layout.template.json.resolver.EventResolverContext;
import org.apache.logging.log4j.layout.template.json.resolver.EventResolverFactory;
import org.apache.logging.log4j.layout.template.json.resolver.TemplateResolverConfig;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Plugin(name = "RunTogetherMdcResolverFactory", category = EventResolverFactory.CATEGORY)
public final class RunTogetherMdcResolverFactory implements EventResolverFactory {

	private static final RunTogetherMdcResolverFactory INSTANCE = new RunTogetherMdcResolverFactory();

	@PluginFactory
	public static RunTogetherMdcResolverFactory getInstance() {
		return INSTANCE;
	}

	@Override
	public String getName() {
		return RunTogetherMdcResolver.getName();
	}

	@Override
	public RunTogetherMdcResolver create(EventResolverContext context, TemplateResolverConfig config) {
		return new RunTogetherMdcResolver(config);
	}
}
