package com.cxwudi.library.slf4j.predefined;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;

public final class MyPredefinedLogger {

	private MyPredefinedLogger() {}
	
	private static boolean hasReset = false;
	
	public static Logger setupInvincibleRootLogger() {
		var logger = (Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		if (!hasReset) resetLogger(logger);
		var appenders = MyPredefinedAppender.createDefaultConsoleAppenders(MyPredefinedEncoder.getMyBestEncoderWithClassName());
		for (ConsoleAppender<ILoggingEvent> consoleAppender : appenders) {
			logger.addAppender(consoleAppender);
		}
		return logger;
	}

	private static void resetLogger(Logger logger) {
		//logger.detachAndStopAllAppenders();
		hasReset = true;
	}
	
}
