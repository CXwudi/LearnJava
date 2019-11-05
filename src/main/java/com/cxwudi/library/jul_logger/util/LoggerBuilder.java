package com.cxwudi.library.jul_logger.util;

import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerBuilder<L extends Logger> {
	
	private L logger;
	
	private LoggerBuilder(L logger){
		this.logger = logger;
	}
	
	public static <L extends Logger> LoggerBuilder<L> setup(L logger) {
		return new LoggerBuilder<>(logger);
	}
	
	public LoggerBuilder<L> setLevel(Level newLevel) {
		logger.setLevel(newLevel);
		return this;
	}
	
	public LoggerBuilder<L> setFilter(Filter newFilter) {
		logger.setFilter(newFilter);
		return this;
	}
	
	public LoggerBuilder<L> setResourceBundle(ResourceBundle bundle) {
		logger.setResourceBundle(bundle);
		return this;
	}
	
	public LoggerBuilder<L> setUseParentHandlers(boolean useParentHandlers) {
		logger.setUseParentHandlers(useParentHandlers);
		return this;
	}
	
	public <H extends Handler>LoggerBuilder<L> addHandlers(H... handlers) {
		for (H h : handlers) {
			logger.addHandler(h);
		}
		return this;
	}
	
	public LoggerBuilder<L> setBy(Consumer<L> setupFunc){
		setupFunc.accept(logger);
		return this;
	}
	
	public L get() {
		return logger;
	}
}
