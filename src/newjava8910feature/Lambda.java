package newjava8910feature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.IntBinaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;


public class Lambda {

	public static void main(String[] args) {
		//first example
        String[] aa = "asdasvfyntfgbsfhge".split("");
        ArrayList<String> aaa = new ArrayList<>();
        aaa.addAll(FXCollections.observableArrayList(aa));
        
        Collections.sort(aaa, new Comparator<String>() { //original way
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
        Collections.sort(aaa, (String a1, String b1) -> {return a1.compareTo(b1);});//lambda way
        Collections.sort(aaa, String::compareTo);
        Collections.sort(aaa, Comparator.naturalOrder());
        
        
		//second example
		//IntBinaryOperator = BinaryOperator<int> = BiFunction<int,int>
		IntBinaryOperator aaBinaryOperator = (int a1,int b1) -> {return Math.max(a1, b1);}; 
        aaBinaryOperator = 					 (a1,b1) -> {return Math.max(a1, b1);};
        aaBinaryOperator = 					 (a1,b1) -> Math.max(a1, b1);
        aaBinaryOperator = 					 Math::max;
        
        System.out.println(aaBinaryOperator.applyAsInt(4, 5));
        
        //third example
        TriFunction<Integer,Integer,Integer,Integer> func = 
				((TriFunction<Integer,Integer,Integer,Integer>) //Do you understand why do we typecast here
				    (Integer i, Integer k, Integer j) -> i+j+k
				).andThen(i -> ++i);
		
		
		var logger = Logger.getGlobal();
		logger.setLevel(Level.ALL);
		logger.log(Level.INFO, "this is the result {0}", func.act(1, 2, 3));
	}

}
