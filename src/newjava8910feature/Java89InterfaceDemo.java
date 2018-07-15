package newjava8910feature;
interface Java89Interface{
	default void add() { //define method in java 8 interface 
		//code...
	};
	default void show() {
		System.out.println("Java8Interface default show");
		show3();
	}
	
	static void show2() {
		System.out.println("Java8Interface static show2");
		
	}
	private void show3() {
		System.out.println("Java9Interface private show3");
	}
	/* error: can not override Object method
	 * 
	default boolean equals(Object obj) {
		return true;
	}
	*/
}

public class Java89InterfaceDemo implements Java89Interface{
	
	public static void main(String[] args) {
		var demo = new Java89InterfaceDemo();
		demo.show();
		Java89Interface.show2();
		
	}
	
}
