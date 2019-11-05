package com.cxwudi.library.jul_logger.util;

import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
/**
 * use this class to setup a logger, then you can replace your
 * {@code System.out} and {@code System.err} with {@code Logger.getGlobal().info()} and 
 * {@code Logger.getGlobal().warning()}. And then make your code looks nicer XD
 * @author CX无敌
 *
 */
public final class LoggerUtil {
	
	public static Level defaultLevel = Level.ALL;
	
	private static boolean hasResetted = false;
	
	private LoggerUtil() {}
	/**
	 * a piece of code to customize the global logger with level {@code Level.ALL} using 
	 * @param handlers a set of handlers
	 */
	public static void setupGlobalLogger(Handler... handlers) {
		if (!hasResetted) LogManager.getLogManager().reset();
		setupLogger(Logger.getGlobal(), handlers);
	}
	
	/**
	 * a piece of code to customize the global logger using
	 * @param level the level of global logger
	 * @param handlers a set of handlers
	 */
	public static void setupGlobalLogger(Level level, Handler... handlers) {
		if (!hasResetted) LogManager.getLogManager().reset();
		setupLogger(Logger.getGlobal(), level, handlers);
	}
	
	/**
	 * configure a logger with defined level using various handlers
	 * @param logger the new logger to be configured
	 * @param handlers a set of handlers
	 * @return logger
	 */
	public static Logger setupLogger(Logger logger, Handler... handlers) {
		return setupLogger(logger, defaultLevel, handlers);
	}
	
	/**
	 * configure a logger with defined level using various handlers
	 * @param logger the new logger to be configured
	 * @param level the logger level
	 * @param handlers a set of handlers
	 * @return logger
	 */
	public static Logger setupLogger(
			Logger logger, 
			Level level, 
			Handler... handlers) {
		return setupLogger(logger, level, null, null, logger.getUseParentHandlers(), handlers);
	}
	
	/**
	 * configure a logger with
	 * @param logger the new logger to be configured
	 * @param level the logger level
	 * @param filter the filter for the logger
	 * @param bundle 
	 * @param useParentHandlers
	 * @param handlers a set of handlers  
	 * @return logger
	 */
	public static Logger setupLogger(
			Logger logger, 
			Level level, 
			Filter filter,
			ResourceBundle bundle,
			boolean useParentHandlers,
			Handler... handlers) {
		if (filter != null) logger.setFilter(filter);
		if (level != null) logger.setLevel(level);
		if (bundle != null) logger.setResourceBundle(bundle);
		if (useParentHandlers != logger.getUseParentHandlers()) logger.setUseParentHandlers(useParentHandlers);
		for (Handler handler : handlers) {
			logger.addHandler(handler);
		}
		return logger;
	}
	
	/**
	 * configure a logger in one line of code
	 * @param logger your logger
	 * @param setupLoggerFunc DIY your logger
	 * @return
	 */
	public static Logger setupLogger(Logger logger, Consumer<Logger> setupLoggerFunc) {
		setupLoggerFunc.accept(logger);
		return logger;
	}
}
