package designpatterns.singleton;

public class UseSingleton {
	
	public static void main(String[] args) {
		
//		Singleton s1 = new Singleton();
		
		Singleton s1 = Singleton.getInstance();
		System.out.println("s1's colour is " + s1.getColour());
		
		Singleton s2 = Singleton.getInstance();
		s2.setColour("green");
		System.out.println("s2's colour is " + s2.getColour());
		System.out.println("s1's colour is " + s1.getColour());
		
//		 compare if the two references are the same
		System.out.println("s1 == s2: " + (s1 == s2));
		
		Integer a = new Integer(3);
		Integer b = new Integer(3);
		System.out.println(a == b);  // compares the reference
		System.out.println(a.equals(b)); // compares the value

	}
}