/**
 * 
 */
package com.cxwudi.library.logger;

import static org.junit.Assert.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

/**
 * @author CX无敌
 *
 */
public class testLogger {

	@Test
	public void testDefaultUsage() {
		LoggerSetup.setupGlobalLogger(Level.ALL, LoggerSetup.createHandler(
				() -> new AutoflushedStreamHandler(System.out), 
				MyPredefinedFormatter.mySimplyFormatter, Level.ALL));
		
		Logger.getGlobal().info("Hello Miku");
	}

}
