package com.srltas.runtogether.common.log;

import java.util.Map;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.layout.template.json.resolver.EventResolver;
import org.apache.logging.log4j.layout.template.json.resolver.TemplateResolverConfig;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;

public final class RunTogetherMdcResolver implements EventResolver {

	private final String key;

	RunTogetherMdcResolver(final TemplateResolverConfig config) {
		this.key = config.getString("key");
	}

	static String getName() {
		return "runTogetherMDC";
	}

	@Override
	public void resolve(LogEvent logEvent, JsonWriter jsonWriter) {
		if ("message".equals(key)) {
			Map<String, Object> messageMap = RunTogetherMDC.getMessage();
			jsonWriter.writeValue(messageMap);
		} else {
			Object value = RunTogetherMDC.get(key);
			jsonWriter.writeValue(value != null ? value : "null");
		}
	}
}
