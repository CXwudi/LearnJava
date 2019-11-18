package com.cxwudi.newjava8910feature;
interface Java89Interface{
    /**
     * All fields in interface MUST have been initialized, by default is <code>public static final</code>,
     * 
     * It is generally recommended to AVOID such interfaces, 
     * since every subclass implement this interface will inheritance all these fields,
     * and changing one field in the future can potentially cause problems.
     * but sometimes you can find an interface that 
     * has no methods and is used only to contain list of constant values.
     */
    public String ASTRING = "public string in interface";
    //private int aint = 5;  Not working
    //protect double adouble = 4.4; Not working
    int BINT = 3;
    static String BSTRING = "static string in interface";
    
	default void add() { //define method in java 8 interface 
		//code...
	}

	default void show() {
		System.out.println("Java8Interface default show");
		show3();
	}
	
	static void show2() {
		System.out.println("Java8Interface static show2");
		
	}

	/*
	 * Intellij doesn't support private method in interface when building a project.
	 * work around is to recompile this class, but currently we just make it private static
	 */
	private static void show3() {
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
		//Java89Interface.ASTRING = "changed public string in interface";     Not working
		System.out.println(Java89Interface.ASTRING + ", " + Java89Interface.BINT + ", " + Java89Interface.BSTRING);
		System.out.println(Java89InterfaceDemo.ASTRING);
	}
	
}
