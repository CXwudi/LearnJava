/**
 * 
 * @author CX无敌
 * @warning Maven with module works, but Eclipse IDE doesn't support multi-module project, and 
 * hence cannot support projects with multiply source folders
 */
module playground {
	exports com.cxwudi.playground;
	exports com.cxwudi.newjava8910feature;
	exports com.cxwudi.leetcode;

	requires java.logging;
	requires javafx.base;
}