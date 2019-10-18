package com.cxwudi.playground;

import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SFL4JLogger {
	public static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public static void main(String[] args) {
		LOGGER.info("Hi Miku");
		LOGGER.warn("Hi Luka");
	}
}
