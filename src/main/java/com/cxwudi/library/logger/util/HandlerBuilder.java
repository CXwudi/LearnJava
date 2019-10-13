package com.cxwudi.library.logger.util;

import java.io.UnsupportedEncodingException;
import java.util.logging.ErrorManager;
import java.util.logging.Filter;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;

/**
 * An alternative way to provide util for setting up handlers
 * @author CX无敌
 *
 */
public class HandlerBuilder {
	
	private Handler handler;
	
	private HandlerBuilder(Handler handlerToBuild) {
		handler = handlerToBuild;
	}
	
	public static HandlerBuilder setup(Handler handler) {
		return new HandlerBuilder(handler);
	}
	
	public HandlerBuilder setLevel(Level newLevel) {
		handler.setLevel(newLevel);
		return this;
	}
	
	public HandlerBuilder setFormatter(Formatter newFormatter) {
		handler.setFormatter(newFormatter);
		return this;
	}
	
	public HandlerBuilder setFilter(Filter newFilter) {
		handler.setFilter(newFilter);
		return this;
	}
	
	public HandlerBuilder setEncoding(String encoding) throws SecurityException, UnsupportedEncodingException {
		handler.setEncoding(encoding);
		return this;
	}
	
	public HandlerBuilder setErrorManager(ErrorManager em) {
		handler.setErrorManager(em);
		return this;
	}
	
	public Handler get() {
		return handler;
	}
}
