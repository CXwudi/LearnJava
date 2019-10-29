package com.cxwudi.library.slf4j.predefined;

import com.cxwudi.library.slf4j.util.SampleLoggerContext;
import com.cxwudi.library.slf4j.util.PatternLayoutEncoderBuilder;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;

public final class MyPredefinedEncoder {
	
	private MyPredefinedEncoder() {}
	
	public static PatternLayoutEncoder getMyBestEncoderWithClassName() {
		return getOrCreateVisierStyleEncoderWithClassName();
	}
	
	public static PatternLayoutEncoder getMyBestEncoderWithLoggerName() {
		return getOrCreateVisierStyleEncoderWithLoggerName();
	}
	
	/**
	 * @see MyPredefinedEncoder#getOrCreateVisierStyleEncoderWithLoggerName()
	 */
	private static PatternLayoutEncoder visierStyleEncoderWithLoggerName = null;
	
	/**
	 * This formatter is been created from inspiration of my Visier coop experience.
	 * It's a simplify version of SLF4J default format,
	 * which is simply, no redundant, no polley info at all, and good looking
	 * @author CX无敌
	 */
	public static PatternLayoutEncoder getOrCreateVisierStyleEncoderWithLoggerName(){
		return getOrCreateImpl(visierStyleEncoderWithLoggerName, "%date{HH:mm:ss.SSS} [%thread] %-5level %logger{10} - %msg%n");
	}
	
	/**
	 * 
	 */
	private static PatternLayoutEncoder visierStyleEncoderWithClassName = null;
	
	/**
	 * This formatter is been created from inspiration of my Visier coop experience.
	 * It's a simplify version of SLF4J default format, 
	 * which is simply, no redundant, no polley info at all, and good looking.
	 * This one supports printing logger caller class name
	 * @author CX无敌
	 */
	public static PatternLayoutEncoder getOrCreateVisierStyleEncoderWithClassName(){
		return getOrCreateImpl(visierStyleEncoderWithClassName, "%date{HH:mm:ss.SSS} [%thread] %-5level %class{10} - %msg%n");
	}
	
	private static PatternLayoutEncoder getOrCreateImpl(PatternLayoutEncoder encoder, String pattern) {
		if (encoder != null) return encoder;
		encoder = PatternLayoutEncoderBuilder.build(new PatternLayoutEncoder())
				.setContext(SampleLoggerContext.get())
				.setPattern(pattern)
				.setImmediateFlush(true)
				.get();
		return encoder;
	}
}
