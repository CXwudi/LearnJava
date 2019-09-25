package com.cxwudi.library;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;
/**
 * use this class to setup a logger, then you can replace sonarLint annoying notification on
 * {@code System.out} and {@code System.err} with {@code Logger.getGlobal().info()} and 
 * {@code Logger.getGlobal().warning()}
 * @author CX无敌
 *
 */
public interface LoggerSetup {
	//a piece of code to customize the global logger
	public static void setupGlobalLogger() {
		LogManager.getLogManager().reset();// remove global logger's default handler
		Logger.getGlobal().setLevel(Level.ALL);
		
		//to customize my own formatter, add millisecond, and make everything in one line
		SimpleFormatter myCustomizeFormatter = new SimpleFormatter() {
			@Override
		    public String format(LogRecord record) {
		        ZonedDateTime zdt = ZonedDateTime.ofInstant(
		                record.getInstant(), ZoneId.systemDefault());
		        String time = zdt.format(DateTimeFormatter.ofPattern("MMM dd, yyyy h:mm:ss.SSS a"));
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
		        if (message.equals("\n")) return "\n";
		        return String.format("%-80s%s %s%n",
		        		message,
		                throwable,
		                String.format(":%s %s %s",
		        				time, 
		        				source,
		        				record.getLevel().getName())
		                );
		    }
		};
		
		StreamHandler handler = new StreamHandler(System.out, myCustomizeFormatter) {
			@Override
			public synchronized void publish(LogRecord record) {
				super.publish(record);
				flush();
			}
		};
		handler.setLevel(Level.ALL);
		Logger.getGlobal().addHandler(handler);
	}
}
