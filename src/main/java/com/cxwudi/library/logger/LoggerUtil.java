package com.cxwudi.library.logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;
/**
 * use this class to setup a logger, then you can replace your
 * {@code System.out} and {@code System.err} with {@code Logger.getGlobal().info()} and 
 * {@code Logger.getGlobal().warning()}. And then make your code looks nicer XD
 * @author CX无敌
 *
 */
public final class LoggerUtil {
	
	public static Level defaultLevel = Level.ALL;
	
	private LoggerUtil() {}
	/**
	 * a piece of code to customize the global logger with level {@code Level.ALL} using 
	 * @param handlers a set of handlers
	 */
	public static void setupGlobalLogger(Handler... handlers) {
		LogManager.getLogManager().reset();// remove global logger's default handler
		setupLogger(Logger.getGlobal(), handlers);
	}
	
	/**
	 * a piece of code to customize the global logger using
	 * @param level the level of global logger
	 * @param handlers a set of handlers
	 */
	public static void setupGlobalLogger(Level level, Handler... handlers) {
		for (Handler handler : Logger.getGlobal().getHandlers()) {
			Logger.getGlobal().removeHandler(handler);
		}
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
		return setupLogger(logger, null, level, null, logger.getUseParentHandlers(), handlers);
	}
	
	/**
	 * configure a logger with
	 * @param logger the new logger to be configured
	 * @param filter the filter for the logger
	 * @param level the logger level
	 * @param bundle 
	 * @param useParentHandlers
	 * @param handlers a set of handlers
	 * @return logger
	 */
	public static Logger setupLogger(
			Logger logger, 
			Filter filter,
			Level level, 
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
