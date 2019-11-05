package com.cxwudi.library.jul_logger.util;

import java.io.UnsupportedEncodingException;
import java.util.function.Consumer;
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
public class HandlerBuilder<H extends Handler> {
	
	private H handler;
	
	private HandlerBuilder(H handlerToBuild) {
		handler = handlerToBuild;
	}
	
	public static <H extends Handler> HandlerBuilder<H> setup(H handler) {
		return new HandlerBuilder<>(handler);
	}
	
	public HandlerBuilder<H> setLevel(Level newLevel) {
		handler.setLevel(newLevel);
		return this;
	}
	
	public HandlerBuilder<H> setFormatter(Formatter newFormatter) {
		handler.setFormatter(newFormatter);
		return this;
	}
	
	public HandlerBuilder<H> setFilter(Filter newFilter) {
		handler.setFilter(newFilter);
		return this;
	}
	
	public HandlerBuilder<H> setEncoding(String encoding) throws SecurityException, UnsupportedEncodingException {
		handler.setEncoding(encoding);
		return this;
	}
	
	public HandlerBuilder<H> setErrorManager(ErrorManager em) {
		handler.setErrorManager(em);
		return this;
	}
	
	public HandlerBuilder<H> setBy(Consumer<H> setupFunc){
		setupFunc.accept(handler);
		return this;
	}
	
	public H get() {
		return handler;
	}
}
