package com.cxwudi.library.slf4j.util;

import java.util.function.Function;

import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * An {@link AbstractMatcherFilter} that override {@link AbstractMatcherFilter#decide(Object)} with
 * a customizable matcher function passed in from constructor
 * @author CX无敌
 *
 * @param <T> 
 */
public class FunctionalMatcherFilter<T> extends AbstractMatcherFilter<T> {

	private Function<T, FilterReply> matcher;
	
	/**
	 * @param matcher the function for defining the filter
	 */
	public FunctionalMatcherFilter (Function<T, FilterReply> matcher) {
		this.matcher = matcher;
	}


	@Override
	public FilterReply decide(T event) {
		return matcher.apply(event);
	}

}
