package com.cxwudi.library.slf4j.util;

import java.util.function.Consumer;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Context;

public class AppenderBuilder<T extends Appender<ILoggingEvent>> {
	
	private T appender;
	
	private AppenderBuilder(T appender) {
		this.appender = appender;
	}
	
	public static <T extends Appender<ILoggingEvent>> AppenderBuilder<T> build(T appender){
		return new AppenderBuilder<>(appender);
	}
	
	public AppenderBuilder<T> setName(String name){
		appender.setName(name);
		return this;
	}
	
	public AppenderBuilder<T> setContext(Context context){
		appender.setContext(context);
		return this;
	}
	
	public AppenderBuilder<T> setByFunc(Consumer<T> setupFunc){
		setupFunc.accept(appender);
		return this;
	}
	
	public T get() {
		appender.start();
		return appender;
	}
}
