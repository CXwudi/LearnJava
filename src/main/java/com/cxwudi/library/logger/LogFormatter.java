/**
 * 
 */
package com.cxwudi.library.logger;

import java.time.format.DateTimeFormatter;

/**
 * A functional interface defining the format of the log message
 * @author CX无敌
 *
 */
@FunctionalInterface
public interface LogFormatter {
	/**
	 * Implement this function to define how does the log message looks like
	 * @param time the time when log is produced, time should be already formatted by the {@link DateTimeFormatter}
	 * @param source the function/method where this log is produced
	 * @param recordLevel the level of the log
	 * @param message what is been log 
	 * @param throwable message of exception
	 * @return a formatted log
	 */
	public String formatLog(String time, String source, String recordLevel, String message, String throwable);
}
