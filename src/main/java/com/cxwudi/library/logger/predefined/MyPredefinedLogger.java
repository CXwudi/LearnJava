package com.cxwudi.library.logger.predefined;

import java.util.logging.Logger;

import com.cxwudi.library.logger.util.LoggerUtil;

/**
 *  A pre-defined handlers set, can trade this class as a wrapper of {@link LoggerUtil}
 * @author CX无敌
 *
 */
public final class MyPredefinedLogger {

	private MyPredefinedLogger() {}
	
	public static void setupInvincibleGlobalLogger() {
		LoggerUtil.setupGlobalLogger(MyPredefinedHandler.createDefaultHandlers(MyPredefinedFormatter.getBestFormatter()));
	}
	
	public static void setupInvincibleLoggerFor(String loggerName) {
		LoggerUtil.setupLogger(Logger.getLogger(loggerName), MyPredefinedHandler.createDefaultHandlers(MyPredefinedFormatter.getBestFormatter()));
	}
}
