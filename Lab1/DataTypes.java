// The class name should match the filename "Types.java"
public class DataTypes {
	public static void main(String[] args) {
		// Start of execution, printing
		System.out.print("Hello ");    // no line break at the end
		System.out.println("World!");  // with line break at the end

		// Primitive types, declaring variables
		// 123 - automatically a short integer
		// 9999.2 - automatically a double

		// auto-conversion, can convert from X to Y iff X "IS-A" Y
		// Intuition: Cat IS-A Animal, calling a cat animal is fine, but not true the other way
		// the IS-A chain: byte -> short -> int -> long -> float -> double
		// go in reverse direction requires casting

		double d = 9991991.2344;
		//d = "This"; // This is a comment
		float f = (float)3222.45;  // cannot auto convert double to float, so must cast explicitly
		long l = -99991919; // 64-bit integer, -9223372036854775808 to 9223372036854775807
		int i = 8236, num_IndexCounter = -17; // 32-bit integer, -2147483648 to 2147483647 
		short s = 128; // 16-bit integer, -32768 to 32767
		byte b = (byte) -7; // 8-bit (1-byte) integer, -128 to 127
		
		// Ex: Convert between b and s
		
		//b = (byte)s;
		//System.out.println(b);
		s = b;
		
		char c = 'T'; // 0..65535 char IS-A int or above
		boolean bo = false; // IS-NOT any of the above
		
		// Ex with boolean
		
		bo = true;
		boolean b1 = !bo;
		
		// Assignment statements, expressions
		/*
		 * Expression evaluated left to right, each sub-expression
		 * converted AS NECESSARY. 
		 */

		d = i; 
		f = b; 
		b = (byte)d; // Assignment expressions
		
		i = 23 + 19 * 2; 
		c = (char)b; 
		d = 3.14159 * 12 * 12;

		d = 1/2;
		d = 1+1/2;
		d = (double)(1/2);
		d = (double)1/2;
		d = 1/2.0;
		System.out.println("d = " + d);
//		
		i = (int)((1.0/2) + ((double)1/2)); // what is i ?
		System.out.println("i = " + i);

		

		// Strings (not primitive type)
		String s0 = "Hey There!", s1 = null;
		String s2 = new String("");
		//s0 = "i=" + i + " and d=" + d;
		//s0 = s0 + " and b=" + b;
		System.out.println("In total "+s0);
		
		//System.out.println("s0.length=" + s0.length());
		//System.out.println("s1.length="+s1.length()); // accessing null reference causes error
		System.out.println("s2.length="+s2.length());
		
		// Strings
		s0 = new String("hello world");
		s2 = "hello";
		
		// like s2[0] in Python
		System.out.println(s2.charAt(0));

		// like s2[0:4] in Python
		System.out.println(s2.substring(0, 4));
				
		String s3 = "    sadf   sd   ";
		System.out.println(s3.trim()); // gets rid of all whitespace
		System.out.println(s3);

		
		/* Operations which return a boolean (true/false)
		 * i==j i<j i<=j i>j i>=j i!=j
		 * Operations on booleans  !   &&   ||  ^
		 *                        NOT  AND  OR XOR
		 */ 
		
		d = 99.1;
		boolean bo1;
		bo1 = true; // cause error if not initialized
//
		boolean bo2 = (99 < d);
//		
		bo = (!(12 > 23)) || (bo1 && bo2 && true);
//		
		System.out.println("bo1="+bo1+" bo2="+bo2+" d="+d+" bo="+bo);
		// That is, b=(!(false))||(true && false && true)) which is 
		// (true || false) which is true
		
		// Integer, Double: Non-primitive types
		// providing more convenience methods, see doc for more details
		Integer ii = new Integer(5);
		Double dd = new Double(5.0);
		Double dd2 = dd;
//	
	}

}
