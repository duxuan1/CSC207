package parsing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FSMExamples {
	
	public static String FSMResult(boolean b) {
		return b ? "ACCEPTS" : "REJECTS";
	}
	
	public static void main(String[] args) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		System.out.print("\nEnter a string: ");
		try {
			while ((line = in.readLine()) != null) {
				
				boolean matches = recognise5Regex(line);
				System.out.println("5Regex " + FSMResult(matches) + ": " + line);
				
				matches = recognise5FSM(line);
				System.out.println("5FSM " + FSMResult(matches) + ": " + line);

				matches = recogniseSomethingRegex(line);
				System.out.println("SomethingRegex " + FSMResult(matches) + ": " + line);

				matches = recogniseSomethingFSM(line);
				System.out.println("SomethingFSM " + FSMResult(matches) + ": " + line);
				
				System.out.print("\nEnter a string: ");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This recogniser uses a regular expression to determine whether s represents a
	 * multiple of 5
	 * 
	 * @param s
	 * @return whether s is the string representation of a multiple of 5
	 */
	public static boolean recognise5Regex(String s) {
		// COMPLETE THIS! ...
		Pattern p = Pattern.compile("^\\d*(0|5)$");
		Matcher m = p.matcher(s);
		return m.matches();
	}

	/**
	 * This recogniser uses a Finite State Machine to determine whether s represents
	 * a multiple of 5
	 * 
	 * @param s
	 * @return whether s is the string representation of a multiple of 5
	 */
	public static boolean recognise5FSM(String s) {
		// COMPLETE THIS! ...

		char[] c = s.toCharArray(); // so you can get a char by c[i]
		int len = s.length();

		int n = 0; 
		int state = 0; // Start out in the initial state
		while (n < len) {
			switch (state) {
			case 0:
				if (c[n] == '0' || c[n] == '5')
					state = 1;
				else if ('0' <= c[n] && c[n] <= '9')
					state = 0;
				else
					state = 2;
				break;
			case 1:
				if (c[n] == '0' || c[n] == '5')
					state = 1;
				else if ('0' <= c[n] && c[n] <= '9')
					state = 0;
				else
					state = 2;
				break;
			case 2:
				break;
			}
			n = n + 1;
		}
		return state == 1;
	}

	// Come up with some strings so that recogniseSomethingRegex returns true
	// Describe what this recognises.
	public static boolean recogniseSomethingRegex(String s) {
		
		Pattern p = Pattern.compile("^(ab){3}(.*):(2|3|4)+(end)+$");
		Matcher m = p.matcher(s);
		if (m.matches()) {
			System.out.println("Groupcount: " + m.groupCount());
			System.out.println("Group 1: " + m.group(1));
			System.out.println("Group 2: " + m.group(2));
			System.out.println("Group 3: " + m.group(3));
			System.out.println("Group 4: " + m.group(4));
			return true;
		} else {
			return false;
		}
	}

	// Come up with some strings so that recogniseSomethingFSM returns true
	// Describe what this recognises.
	public static boolean recogniseSomethingFSM(String s) {
		
		char[] c = s.toCharArray();
		int len = s.length();
		// We can now access the characters of s one at a time via c[0], c[1], ...,
		// c[len-1]

		int n = 0;
		int state = 0; // Start out in the initial state
		while (n < len) {
			switch (state) {
			case 0:
				if ('a' <= c[n] && c[n] <= 'z')
					state = 0;
				else if ('0' <= c[n] && c[n] <= '9')
					state = 1;
				else
					state = 2;
				break;
			case 1:
				if ('0' <= c[n] && c[n] <= '9')
					state = 1;
				else
					state = 2;
				break;
			case 2:
				break;
			}
			n = n + 1;
		}
		return state == 0 || state == 1;
	}
}
