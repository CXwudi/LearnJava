package com.cxwudi.library.logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;
import java.util.logging.ErrorManager;
import java.util.logging.Filter;
import java.util.logging.Formatter;
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
public final class LoggerSetup {
	
	private LoggerSetup() {}
	
	
	//a piece of code to customize the global logger
	public static void setupGlobalLogger(Level level, Handler... handlers) {
		LogManager.getLogManager().reset();// remove global logger's default handler
		setupLogger(Logger::getGlobal, Level.ALL, handlers);
	}
	
	/**
	 * configure a logger with defined level using various handlers
	 * @param loggerSupplier the function that create a new logger to be configured
	 * @param level the logger level
	 * @param handlers a set of handler
	 * @return logger
	 */
	public static Logger setupLogger(
			Supplier<Logger> loggerSupplier, 
			Level level, 
			Handler... handlers) {
		Logger logger = loggerSupplier.get();
		logger.setLevel(level);
		for (Handler handler : handlers) {
			logger.addHandler(handler);
		}
		return logger;
	}
	/**
	 * Create a handler using 
	 * @param handlerSupplier a function to create an instance of Handler
	 * @param formatter the log format
	 * @param level
	 * @return a new handler
	 */
	public static Handler createHandler(
			Supplier<Handler> handlerSupplier, 
			Formatter formatter, 
			Level level) {
		return setupHandler(handlerSupplier, formatter, level, null, null);
	}

	/**
	 * create a handler using
	 * @param handlerSupplier a function to create an instance of Handler
	 * @param formatter the log format
	 * @param level 
	 * @param errorManager
	 * @param encoding
	 * @return a new handler
	 */
	public static Handler setupHandler(Supplier<Handler> handlerSupplier, 
			Formatter formatter, 
			Level level,
			ErrorManager errorManager,
			String encoding) {
		Handler handler = handlerSupplier.get();
		if (formatter != null) handler.setFormatter(formatter);
		if (level != null) handler.setLevel(level);
		if (encoding != null && !encoding.equals("")) {
			try {
				handler.setEncoding(encoding);
			} catch (SecurityException | UnsupportedEncodingException e) {
				System.err.println("fail to set encoding " + encoding + " for " + handler);
			}
		}
		if (errorManager != null) handler.setErrorManager(errorManager);
		return handler;
	}
	
	/**
	 * Run to show sample usage
	 * @param args
	 */
	public static void main(String[] args) {
		setupGlobalLogger(Level.ALL, createHandler(
				() -> new AutoflushedStreamHandler(System.out), 
				MyPredefinedFormatter.nokiaStyleFormatter, 
				Level.ALL)
		);
		
		Logger.getGlobal().info("Hello Miku");
		
	}
}
