/**
 * 
 */
package com.cxwudi.library.slf4j;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cxwudi.library.slf4j.predefined.MyPredefinedAppender;
import com.cxwudi.library.slf4j.predefined.MyPredefinedEncoder;
import com.cxwudi.library.slf4j.predefined.MyPredefinedLogger;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;

/**
 * @author CX无敌
 *
 */
class SLF4JLoggerTest {

	@Test
	void testCreatingAppenders() {
		Appender<ILoggingEvent>[] array = MyPredefinedAppender.createDefaultConsoleAppenders(MyPredefinedEncoder.getOrCreateVisierStyleEncoderWithClassName());
	}

	@Test
	void testRootLogger() {
		var logger = MyPredefinedLogger.setupInvincibleRootLogger();
		
		logger.info("Hi Miku, from code");
		logger.warn("Hi Vocaloid");
	}
}
