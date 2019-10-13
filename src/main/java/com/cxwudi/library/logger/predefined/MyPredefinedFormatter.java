package com.cxwudi.library.logger.predefined;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Objects;
import java.util.function.UnaryOperator;

import com.cxwudi.library.logger.util.CustomizableFormatter;

/**
 * A set of my own log formatter, use it when setting up a logger {@link Handler}
 * using {@link HandlerUtil} or {@link MyPredefinedHandler}
 * @author 11134
 *
 */
public final class MyPredefinedFormatter {

	private MyPredefinedFormatter() {
	}
	
	/**
	 * In these many pre-defined formatter, the one I liked the most is currently
	 * <h1>{@link MyPredefinedFormatter#visierStyleFormatter}</h1>
	 * @return
	 */
	public static CustomizableFormatter getBestFormatter() {
		return getOrCreateVisierStyleFormatter();
	}
	
	/**
	 * Same as {@link MyPredefinedFormatter#SimpleFormatter} but it's in one line
	 */
	private static CustomizableFormatter mySimplyFormatter;
	
	/**
	 * Lazely created or get {@linkplain MyPredefinedFormatter#mySimplyFormatter}
	 * @return
	 */
	public static CustomizableFormatter getOrCreateMySimplyFormatter() {
		if (Objects.isNull(mySimplyFormatter)) {
			mySimplyFormatter = new CustomizableFormatter(
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
		}
		return mySimplyFormatter;
	}
	
	/**
	 * @see {@link MyPredefinedFormatter#getOrCreateNokiaStyleFormatter()}
	 */
	private static CustomizableFormatter nokiaStyleFormatter = null;
	
	/**
	 * This formatter is been created from inspiration of my Nokia coop experience,
	 * Called Nokia formatter for short, This formatter keep log concise, but still informative
	 * @author CX无敌
	 */
	public static CustomizableFormatter getOrCreateNokiaStyleFormatter() {
		if(Objects.isNull(nokiaStyleFormatter)) {
			nokiaStyleFormatter = new CustomizableFormatter(new DateTimeFormatterBuilder()
					.appendLiteral('[')
					.appendPattern("MM/dd/yy")
					.appendLiteral(' ')
					.appendPattern("HH:mm:ss.SSS")
					.appendLiteral(']')
					.toFormatter(),
					(time,source, recordLevel, message, throwable) -> {
						if (message.equals("\n")) return "\n";
						return String.format("%s %s:%s: %s %s%n",
				        		time, recordLevel.toLowerCase(), source, message, throwable);
					}
					);
		}
		return nokiaStyleFormatter;
		
	}
	
	/**
	 * @see {@link MyPredefinedFormatter#getOrCreateVisierStyleFormatter}
	 */
	private static CustomizableFormatter visierStyleFormatter = null;
	
	/**
	 * This formatter is been created from inspiration of my Visier coop experience,
	 * which is simply, no redundant, no polley info at all, and good looking
	 * @author CX无敌
	 */
	public static CustomizableFormatter getOrCreateVisierStyleFormatter() {
		if(visierStyleFormatter != null) return visierStyleFormatter;
		// a function that can shorten a source name like "com.cxwudi.library.logger.LoggerUtilTest" to "c.c.l.l.testLogger"
		final UnaryOperator<String> shortenPathFunction = source -> {
			var newSource = new StringBuilder();
			if (source.contains(".")) {
				String[] sourceArray = source.split("\\.");
				for (int i = 0; i < sourceArray.length - 1; i++) {
					newSource.append(sourceArray[i].charAt(0)).append(".");
				}
				newSource.append(sourceArray[sourceArray.length - 1]);
			} else {
				newSource.append(source);
			}
			return newSource.toString();
		};
		visierStyleFormatter = new CustomizableFormatter(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"),
				(time,source, recordLevel, message, throwable) -> {
					if (message.equals("\n")) return "\n";
					return String.format("%s [%s] %s - %s %s%n",
			        		time, 
			        		recordLevel.subSequence(0, 4), 
			        		shortenPathFunction.apply(source), 
			        		message, 
			        		throwable);
				});
		return visierStyleFormatter;
				
	}
			

}
