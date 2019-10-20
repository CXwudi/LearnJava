package com.cxwudi.library.slf4j.util;

import java.util.function.Consumer;

import org.slf4j.Logger;

public class LoggerBuilder<T extends Logger> {

	private T logger;
	
	private LoggerBuilder(Logger logger, Class<T> class1) {
		this.logger = class1.cast(logger);
	}
	
	public static <T extends Logger> LoggerBuilder<T> build(Logger logger, Class<T> class1){
		return new LoggerBuilder<>(logger, class1);
	}
	
	public LoggerBuilder<T> setByFunc(Consumer<T> setupFunc){
		setupFunc.accept(logger);
		return this;
	}
	
	public T get() {
		return logger;
	}
	
	
}
