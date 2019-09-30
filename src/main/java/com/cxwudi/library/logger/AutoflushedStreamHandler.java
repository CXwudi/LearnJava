package com.cxwudi.library.logger;

import java.io.OutputStream;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

/**
 * Basically a {@link StreamHandler} that calls .flush() everytime a new log is published.
 * So logger using this StreamHandler will always print log immediately.
 * @author CX无敌
 *
 */
public class AutoflushedStreamHandler extends StreamHandler {

	public AutoflushedStreamHandler() {
	}

	public AutoflushedStreamHandler(OutputStream out) {
		super(out, new SimpleFormatter());
	}
	
	public AutoflushedStreamHandler(OutputStream out, Formatter formatter) {
		super(out, formatter);
	}
	
	/**
	 * Format and print log immediately
	 */
	@Override
	public synchronized void publish(LogRecord record) {
		super.publish(record);
		flush();
	}

}
