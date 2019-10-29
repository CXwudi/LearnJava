package com.cxwudi.library.slf4j.util;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

public final class SampleLoggerContext {
	private static LoggerContext rootLoggerContext = null;
	
	private SampleLoggerContext() {}

	public static synchronized LoggerContext get() {
		if (rootLoggerContext != null) return rootLoggerContext;
		rootLoggerContext = ((Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME)).getLoggerContext();
		rootLoggerContext.reset();
		return rootLoggerContext;
	}
}
