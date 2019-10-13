package com.cxwudi.library.logger.predefined;

import com.cxwudi.library.logger.util.LoggerUtil;

/**
 *  A pre-defined handlers set, can trade this class as a wrapper of {@link LoggerUtil}
 * @author CX无敌
 *
 */
public final class MyPredefinedLogger {

	private MyPredefinedLogger() {}
	
	public static void setupInvincibleGlobalLogger() {
		LoggerUtil.setupGlobalLogger(MyPredefinedHandler.createDefaultHandler(MyPredefinedFormatter.getBestFormatter()));
	}
}
