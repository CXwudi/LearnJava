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
		LoggerUtil.setupGlobalLogger(
				HandlerUtil.setupHandler(new AutoflushedStreamHandler(System.out),
				MyPredefinedFormatter.nokiaStyleFormatter));
		
		Logger.getGlobal().info("Hello Miku");
	}

}
