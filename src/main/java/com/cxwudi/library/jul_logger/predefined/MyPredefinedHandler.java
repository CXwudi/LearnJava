package com.cxwudi.library.jul_logger.predefined;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;

import com.cxwudi.library.jul_logger.util.AutoflushedStreamHandler;
import com.cxwudi.library.jul_logger.util.HandlerUtil;
/**
 * A pre-defined handlers set, can trade this class as a wrapper of {@link HandlerUtil}
 * @author CX无敌
 *
 */
public final class MyPredefinedHandler {
	
	private MyPredefinedHandler() {}
	
	/**
	 * A set of handlers that can make a logger log normal message in {@code System.out} and warning/error message in {@code System.err}
	 * @param formatter the format of the log 
	 * @return array of handlers
	 */
	public static Handler[] createDefaultHandlers(Formatter formatter) {
		var handler1 = HandlerUtil.setupHandler(new AutoflushedStreamHandler(System.err), formatter, Level.WARNING);
		var handler2 = HandlerUtil.setupHandler(new AutoflushedStreamHandler(System.out), formatter, Level.ALL, log -> log.getLevel().intValue() <= Level.INFO.intValue());
		return new Handler[] {handler1, handler2};
	}
	
	/**
	 * A file handler 
	 * @param file the file to write logs
	 * @param formatter
	 * @return
	 * @throws SecurityException
	 * @throws IOException
	 */
	public static Handler[] createFileHandler(File file, Formatter formatter) throws SecurityException, IOException {
		var handler1 = HandlerUtil.setupHandler(new FileHandler(file.getAbsolutePath()), formatter);
		return new Handler[] {handler1};
	}
}
