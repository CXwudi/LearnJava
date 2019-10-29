package com.cxwudi.library.slf4j.util;

import java.nio.charset.Charset;
import java.util.function.Consumer;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.Layout;

/**
 * A facade to conviencly create a new PatternLayoutEncoder within one line of code, sample usage:
 * 
 * {@code PatternLayoutEncoderBuilder.build(new PatternLayoutEncoder()).setPattern(pattern).setImmediateFlush(true).get();}
 * @author CX无敌
 *
 * @param <T> class or subclass of PatternLayoutEncoder
 */
public class PatternLayoutEncoderBuilder<T extends PatternLayoutEncoder> {
	
	private T encoder;
	
	private PatternLayoutEncoderBuilder(T encoder){
		this.encoder = encoder;
	}
	
	public static <T extends PatternLayoutEncoder> PatternLayoutEncoderBuilder<T> build(T encoder){
		return new PatternLayoutEncoderBuilder<>(encoder);
	}
	
	/**
	 * @see PatternLayoutEncoder#setCharset(Charset)
	 */
	public PatternLayoutEncoderBuilder<T> setCharset(Charset charset){
		encoder.setCharset(charset);
		return this;
	}
	
	/**
	 * @see PatternLayoutEncoder#setContext(Context)
	 * @param context
	 * @return this PatternLayoutEncoderBuilder
	 */
	public PatternLayoutEncoderBuilder<T> setContext(Context context){
		encoder.setContext(context);
		return this;
	}
	
	/**
	 * @see PatternLayoutEncoder#setImmediateFlush(boolean)
	 * @param immediateFlush
	 * @return this PatternLayoutEncoderBuilder
	 */
	public PatternLayoutEncoderBuilder<T> setImmediateFlush(boolean immediateFlush){
		encoder.setImmediateFlush(immediateFlush);
		return this;
	}
	
	/**
	 * @see PatternLayoutEncoder#setLayout(Layout)
	 * @param layout
	 * @return this PatternLayoutEncoderBuilder
	 */
	public PatternLayoutEncoderBuilder<T> setLayout(Layout<ILoggingEvent>layout){
		encoder.setLayout(layout);
		return this;
	}
	
	/**
	 * @see PatternLayoutEncoder#setOutputPatternAsHeader(boolean)
	 * @param outputPatternAsHeader
	 * @return this PatternLayoutEncoderBuilder
	 */
	public PatternLayoutEncoderBuilder<T> setOutputPatternAsHeader(boolean outputPatternAsHeader){
		encoder.setOutputPatternAsHeader(outputPatternAsHeader);
		return this;
	}
	
	/**
	 * @see PatternLayoutEncoder#setPattern(String)
	 * @param pattern
	 * @return this PatternLayoutEncoderBuilder
	 */
	public PatternLayoutEncoderBuilder<T> setPattern(String pattern){
		encoder.setPattern(pattern);
		return this;
	}
	
	public PatternLayoutEncoderBuilder<T> setByFunc(Consumer<T> setupFunc){
		setupFunc.accept(encoder);
		return this;
	}
	
	/**
	 * @return the encoder
	 */
	public T get() {
		encoder.start();
		return encoder;
	}
	
}
