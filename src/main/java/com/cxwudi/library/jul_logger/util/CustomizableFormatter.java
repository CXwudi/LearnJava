/**
 * 
 */
package com.cxwudi.library.jul_logger.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/**
 * A customizable formatter that put all messages in one line, and ignore printing
 * @author CX无敌
 *
 */
public class CustomizableFormatter extends SimpleFormatter {
	/**
	 * The customized date format
	 */
	private DateTimeFormatter dateFormatter;
	/**
	 * the customized log format
	 */
	private LogFormatter logFormatter;

	/**
	 * create a formatter using customized date format and log format
	 * @param dateFormatter your customized date format
	 * @param logFormatter your customized log format
	 */
	public CustomizableFormatter(DateTimeFormatter dateFormatter, LogFormatter logFormatter) {
		this.dateFormatter = dateFormatter;
		this.logFormatter = logFormatter;
	}

	/**
	 * Format the given message using customized {@link DateTimeFormatter} and {@link LogFormatter}
	 */
	@Override
    public String format(LogRecord record) {
        ZonedDateTime zdt = ZonedDateTime.ofInstant(
                record.getInstant(), ZoneId.systemDefault());
        String time = zdt.format(dateFormatter);
        String source;
        if (record.getSourceClassName() != null) {
            source = record.getSourceClassName();
            if (record.getSourceMethodName() != null) {
               source += " " + record.getSourceMethodName();
            }
        } else {
            source = record.getLoggerName();
        }
        String message = formatMessage(record);
        String throwable = "";
        if (record.getThrown() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            pw.println();
            record.getThrown().printStackTrace(pw);
            pw.close();
            throwable = sw.toString();
        }
        return logFormatter.formatLog(
        		time, 
        		source, 
        		record.getLevel().getName(), 
        		message, 
        		throwable);
                
    }
	
	
}
