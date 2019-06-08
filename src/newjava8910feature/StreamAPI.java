package newjava8910feature;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamAPI {

	public static boolean checkPrime(int x) {
		return x > 1 && IntStream.range(2, x).map(i -> x % i).noneMatch(i -> i == 0);
	}
	
	public static List<Integer> getDivisors(int x){
		return IntStream.rangeClosed(1, x)
				.parallel()
				//.peek(System.out::println)
				.filter(i -> x % i ==0)
				.mapToObj(Integer::valueOf) // same as .boxed()
				.sorted()
				.collect(Collectors.toList());
	}
	
	public static List<Integer> getCommonDivisor(int x, int y) {
		return x > y ? getCommonDivisor(y, x) : 
			IntStream.rangeClosed(1, x)
			.parallel()
			.filter(i -> x % i == 0 && y % i == 0)
			.boxed()// .mapToObj(Integer::valueOf) // are the same
			.collect(Collectors.toList());
	}
	
	public static void testCollect(int r) {
		ArrayList list = IntStream.rangeClosed(0, r).parallel()
				.collect(ArrayList<Integer>::new, (l,n) -> l.add(n), (l1, l2)->l1.addAll(l2));
		ArrayList list2 = IntStream.rangeClosed(0, r).parallel()
				.collect(ArrayList<Integer>::new, ArrayList<Integer>::add, ArrayList<Integer>::addAll);
		System.out.println(list);
		System.out.println(list2);
	}
	
	public static void testSort(ArrayList<Integer> list) {
		var l1 = list.stream();
		var l2 = new ArrayList<>(list);
		var start = System.nanoTime();
		System.out.println("start = " + start);
		l1.sorted().close();// why is it way faster than second way? 
		System.out.println("end = " + (System.nanoTime() - start));
		start = System.nanoTime();
		System.out.println("start = " + start);
		l2.sort(Comparator.naturalOrder());
		System.out.println("end = " + (System.nanoTime() - start));
		
		
	}
	
	public static void main(String[] args) {
		System.out.println("79 is " + (checkPrime(79) ? "prime" : "not prime"));
		new Thread(() -> System.out.println(getDivisors(34578636))).start();
		new Thread(() -> System.out.println(getDivisors(210983748))).start();
		System.out.println(getCommonDivisor(34578636,210983748));
		//testCollect(10);
		//var testList = IntStream.range(0, 10000000).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toCollection(ArrayList::new));
		//System.out.println(testList);
		//testSort(testList);
	}
	
}
