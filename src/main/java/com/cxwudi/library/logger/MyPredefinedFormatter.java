package com.cxwudi.library.logger;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * A set of my own log formatter, use it when setting up a logger {@link Handler}
 * @author 11134
 *
 */
public final class MyPredefinedFormatter {

	private MyPredefinedFormatter() {
	}
	
	/**
	 * Same as {@link SimpleFormatter} but it's in one line
	 */
	public static final CustomizableFormatter mySimplyFormatter = new CustomizableFormatter(
			DateTimeFormatter.ofPattern("MMM dd, yyyy h:mm:ss.SSS a"), 
			(time,source, recordLevel, message, throwable) -> {
				if (message.equals("\n")) return "\n";
				return String.format("%s: %s %s%n",
		        		String.format("%s %s %s",
		        				time, 
		        				source,
		        				recordLevel),
		        		message,
		                throwable
		                );
			});
	
	/**
	 * This formatter is been created from inspiration of Nokia's switches embedded software logger format.
	 * Called Nokia formatter for short, This formatter keep log concise, but still informative
	 * @author CX无敌
	 *
	 */
	public static final CustomizableFormatter nokiaStyleFormatter = new CustomizableFormatter(
			new DateTimeFormatterBuilder()
			.appendLiteral('[')
			.appendPattern("MM/dd/yy")
			.appendLiteral(' ')
			.appendPattern("HH:mm:ss.SSS")
			.appendLiteral(']')
			.toFormatter(),
			(time,source, recordLevel, message, throwable) -> {
				if (message.equals("\n")) return "\n";
				return String.format("%s %s:%s: %s %s%n",
		        		time, recordLevel, source, message, throwable);
			}
			);

}
