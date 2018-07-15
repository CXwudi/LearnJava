package newjava8910feature;


import java.util.Set;
import java.util.stream.IntStream;

public class StreamAPI {

	public static boolean checkPrime(int x) {
		return x > 1 && IntStream.range(2, x).map(i -> x % i).noneMatch(i -> i == 0);
	}
	
	public static Set<Integer> getDivisors(int x){
		return null;//TODO: Can you use stream api to do an one line code
	}
	
	public static void main(String[] args) {
		System.out.println(checkPrime(79));
	}
	
}
