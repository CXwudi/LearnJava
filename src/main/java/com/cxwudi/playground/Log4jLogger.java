package com.cxwudi.playground;

import java.io.File;

import org.apache.logging.log4j.LogManager;

public class Log4jLogger {

	public static void main(String[] args) {
//		System.setProperty("log4j2.configurationFile", new File("src/main/java/com/cxwudi/playground/log4j2.xml").toString());
//		System.setProperty("log4j.skipJansi", "false");
		//above system property is been replaced by a property file in src/main/resources
		var logger = LogManager.getLogger();
		logger.info("Miku??");
		logger.warn("Luka!!");
		logger.error("Oh no");
		logger.fatal("Meiko coming");
	}

}
