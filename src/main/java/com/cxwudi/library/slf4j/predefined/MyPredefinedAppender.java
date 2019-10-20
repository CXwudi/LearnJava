package com.cxwudi.library.slf4j.predefined;

import java.lang.reflect.Array;

import com.cxwudi.library.slf4j.util.AppenderBuilder;
import com.cxwudi.library.slf4j.util.FunctionalMatcherFilter;
import com.cxwudi.library.slf4j.util.SampleLoggerContext;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.filter.LevelFilter;
import ch.qos.logback.classic.filter.ThresholdFilter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.encoder.Encoder;
import ch.qos.logback.core.spi.FilterReply;

public final class MyPredefinedAppender {
	private MyPredefinedAppender() {}
	
	public static ConsoleAppender<ILoggingEvent>[] createDefaultConsoleAppenders (Encoder<ILoggingEvent> encoder) {
		var stdoutAppender = AppenderBuilder.build(new ConsoleAppender<ILoggingEvent>())
				.setContext(SampleLoggerContext.get())
				.setByFunc(appender -> {
			appender.setEncoder(encoder);
			appender.setImmediateFlush(true);
			appender.setTarget("System.out");
			appender.addFilter(new FunctionalMatcherFilter<ILoggingEvent>(e -> {
				var level = e.getLevel();
				if (level.isGreaterOrEqual(Level.WARN)) return FilterReply.DENY;
				else return FilterReply.NEUTRAL;
			}));
		}).get();
		
		var stderrAppender = AppenderBuilder.build(new ConsoleAppender<ILoggingEvent>())
				.setContext(SampleLoggerContext.get())
				.setByFunc(appender -> {
			appender.setEncoder(encoder);
			appender.setImmediateFlush(true);
			appender.setTarget("System.err");
			var filter = new ThresholdFilter();
			filter.setLevel(Level.WARN.toString());
			filter.start();
			appender.addFilter(filter);
		}).get();
		
		@SuppressWarnings("unchecked")
		var array = (ConsoleAppender<ILoggingEvent>[]) Array.newInstance(stderrAppender.getClass(), 2);
		array[0] = stdoutAppender;
		array[1] = stderrAppender;
		return array;
	}
}
