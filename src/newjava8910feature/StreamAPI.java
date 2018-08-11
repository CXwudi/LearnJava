package newjava8910feature;


import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.IntPredicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamAPI {

	public static boolean checkPrime(int x) {
		return x > 1 && IntStream.range(2, x).map(i -> x % i).noneMatch(i -> i == 0);
	}
	
	public static List<Integer> getDivisors(int x){
		return IntStream.rangeClosed(1, x)
				.parallel()
				.filter(i -> x % i ==0)
				.mapToObj(Integer::valueOf)
				.sorted()
				.collect(Collectors.toList());
	}
	
	public static List<Integer> getCommonDivisor(int x, int y) {
		return x > y ? getCommonDivisor(y, x) : IntStream.rangeClosed(1, x).parallel().filter(i -> x % i == 0 && y % i == 0).mapToObj(Integer::valueOf).collect(Collectors.toList());
	}
	
	public static void main(String[] args) {
		System.out.println("79 is " + (checkPrime(79) ? "prime" : "not prime"));
		new Thread(() -> System.out.println(getDivisors(34578636))).start();
		new Thread(() -> System.out.println(getDivisors(210983748))).start();
		System.out.println(getCommonDivisor(34578636,210983748));
	}
	
}
