package com.cxwudi.playground;

import java.lang.invoke.MethodHandles;

import org.slf4j.LoggerFactory;

public class SFL4JLogger {

	public static void main(String[] args) {
		var logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
		logger.info("Hi Miku");
	}
}
