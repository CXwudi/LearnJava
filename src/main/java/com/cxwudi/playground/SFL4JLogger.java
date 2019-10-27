package com.cxwudi.playground;

import java.io.File;
import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import ch.qos.logback.classic.util.ContextInitializer;

public class SFL4JLogger {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	public static void main(String[] args) {
		//System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, new File("src/main/java/com/cxwudi/playground/logback.xml").toString());
		logger.info("Hi Miku");
		logger.warn("Hi Luka");
		logger.error("Oh no");
		logger.debug(new File("src/main/java/com/cxwudi/playground/logback.xml").getAbsolutePath());
	}
}
