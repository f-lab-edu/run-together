package com.srltas.runtogether.common.log;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RunTogetherMDC {

	private static final ThreadLocal<Map<String, Object>> contextMap = ThreadLocal.withInitial(() -> {
		Map<String, Object> context = new HashMap<>();
		context.put("message", new LinkedHashMap<>());
		return context;
	});

	public static void put(String key, Object value) {
		contextMap.get().put(key, value);
	}

	public static Object get(String key) {
		return contextMap.get().get(key);
	}

	public static void remove(String key) {
		contextMap.get().remove(key);
	}

	public static void clear() {
		contextMap.get().clear();
	}

	public static Map<String, Object> getContextMap() {
		return contextMap.get();
	}

	public static void putMessage(String key, Object value) {
		Map<String, Object> messageMap = getMessage();
		messageMap.put(key, value);
	}

	public static Object getMessage(String key) {
		Map<String, Object> messageMap = getMessage();
		return messageMap.get(key);
	}

	public static Map<String, Object> getMessage() {
		return (Map<String, Object>)contextMap.get().get("message");
	}

	public static void clearMessage() {
		Map<String, Object> messageMap = getMessage();
		messageMap.clear();
	}
}
