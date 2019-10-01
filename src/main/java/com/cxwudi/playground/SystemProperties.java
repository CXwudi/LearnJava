package com.cxwudi.playground;

import java.util.logging.Logger;


public class SystemProperties {

	public static void main(String[] args) {
//		COMP3303LoggerSetup.setupGlobalLogger();
		Logger.getGlobal().info(System.getProperty("user.name"));
	}

}
