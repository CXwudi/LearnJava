package com.cxwudi.library.logger;

import java.io.UnsupportedEncodingException;
import java.util.function.Consumer;
import java.util.logging.ErrorManager;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
/**
 * An util class for setting up logger handlers
 * @author CX无敌
 *
 */
public final class HandlerUtil {
	
	public static final Level DEFAULT_LEVEL = Level.ALL;
	
	private HandlerUtil() {}
	
	/**
	 * help setup a handler in one line of code with a default {@code Level.All} using
	 * @param handler
	 * @param formatter
	 * @return
	 */
	public static Handler setupHandler(
			Handler handler, 
			Formatter formatter) {
		return setupHandler(handler, formatter, DEFAULT_LEVEL, null, null);
	}

	/**
	 * help setup a handler in one line of code using 
	 * @param handler the Handler to be set
	 * @param formatter the log format
	 * @param level
	 * @return a new handler
	 */
	public static Handler setupHandler(
			Handler handler, 
			Formatter formatter, 
			Level level) {
		return setupHandler(handler, formatter, level, null, null);
	}

	/**
	 * help setup a handler in one line of code using 
	 * @param handler the Handler to be set
	 * @param formatter the log format
	 * @param level 
	 * @param errorManager
	 * @param encoding
	 * @return a new handler
	 */
	public static Handler setupHandler(
			Handler handler, 
			Formatter formatter, 
			Level level,
			ErrorManager errorManager,
			String encoding) {
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
	 * help setup a handler in one line of code using 
	 * @param handler the Handler to be set
	 * @param setupLoggerFunc DIY your own handler
	 * @return
	 */
	public static Handler setupHandler(Handler handler, Consumer<Handler> setupLoggerFunc) {
		setupLoggerFunc.accept(handler);
		return handler;
	}

}
