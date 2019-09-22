package com.cxwudi.newjava8910feature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.logging.Logger;

interface TriFunction<T, K, U, R> {
	
	public R act(T t,K k,U u);
	
	default <V> TriFunction<T, K, U, V> andThen(Function<? super R, ? extends V> f) {//Tricky question, why super, why extend
		Objects.requireNonNull(f);
		return (T t,K k,U u) -> f.apply(act(t,k,u));
	}
}
public class GenericWildcard {
	//wildcard often used in variable type declaration
	public static <T, V extends List<? super T>> List<? super T> aTestFunc(V list, T toadd) {
		list.add(toadd);//add some variable of type 
		//I made a change by VScode
		//I made a change by eclipse
		return list;
		
	}
	public static <T, V extends Set<T>> V returnSet() {
		//return new HashSet<T>(); //Do you understand why this does not work
		return null;
	}
	public static void main(String[] args) {
		assert 5 > 6 : "if the condition is false, program compiled with java -es will throw error here";
		
		var logger = Logger.getGlobal();
		logger.info(aTestFunc(new ArrayList<>(Arrays.asList(5,6,7,8)), 9.0).toString());
		ArrayList<? super Integer> name = new ArrayList<Number>();
	}
}
