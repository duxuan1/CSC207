package regex;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A demo of Java regular expressions.
 * @author campbell
 * 
 * https://regex101.com [regex101.com]
 * https://regexone.com [regexone.com]
 * https://regexcrossword.com [regexcrossword.com]
 * https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
 * https://docs.oracle.com/javase/tutorial/essential/regex/
 * http://stackoverflow.com/questions/5319840/greedy-vs-reluctant-vs-possessive-quantifiers
 */
/**
 * 
 * Want to find patterns in strings 
 * /abcd/ matches "abcd" 
 * /a+bcd/ + matches 1 or more of previous 
 * 		matches "abcd", "aabcd", "aaaaaabcd" 
 * 		wont match "bcd", "aaacd" 
 * /a*bcd/ * matches 0 or more of previous 
 * 		matches "abcd" "aabcd", "aaaaaabcd", "bcd" 
 * 		wont match "bbbcd" 
 * /a?bcd/ ? matches 0 or 1 of previous
 * 		matches "abcd", "bcd" 
 * 		wont match "aaaabcd", "cd" 
 * /(a|z)bcd/ | matches left or right 
 * 		matches "abcd", "zbcd" 
 * 		wont match "aabcd"
 * 
 * . matches a single character (any single character)
 * \d matches a single digit, equivalent to [0-9]
 * [0-9] equivalent to  (0|1|2|3|4|5|6|7|8|9)
 * [a-z] equivalent to (a|b|...|z)
 * {n} matches n times previous
 * [aeiouy] matches any lowercase vowel
 * [^aeiouy] matches any non lowercase vowel

Regular expression: \d\d\d-\d\d\d-\d\d\d\d
String: 416-555-1234
true
Regular expression: 
String: 416-222-33456
false
Regular expression: 
String: 889-1234
false
Regular expression: (\d\d\d-\d\d\d-\d\d\d\d)|(\d\d\d-\d\d\d\d)
String: 416-555-1234
true
Regular expression: 
String: 889-1234
true
Regular expression: 
String: 416-222-33456
false
Regular expression: (\d\d\d-)?\d\d\d-\d\d\d\d
String: 
false
Regular expression: (\d{3}-)?\d{3}-\d{4}
String: 416-555-1234
true
Regular expression: 
String: 416-222-33456
false
Regular expression: [a-zA-Z]\d[a-zA-Z]\s*-?\s*\d[a-zA-Z]\d
String: l5L3m3
true
Regular expression: 
String: L5l  -   3m3
true

To match lines in a CSV file with format

	Name,StudentNumber,mark

Examples:

sid smith,111111111,23
jane samantha doe,0123456789,100

attempts:

(.*),(.*),(.*)
(.*),(\d*),(.*)
(.*),(\d{9}|\d{10}),(.*)
(.*),(\d{9}\d?),(.*)
(.*),(\d{9}\d?),((100)|([1-9]?\d))
(.*),(\d{9,10}),((100)|([1-9]?\d))
 */

public class RegexMatcher {

	/**
	 * Prompts the user to enter a regular expression and a string, and reports
	 * whether that regular expression matches the string. The user types quit to
	 * exit.
	 */
	public static void doMatching() {
		try {
			BufferedReader lineInput = new BufferedReader(new InputStreamReader(System.in));
			String oldRe = "";

			while (true) {
				System.out.print("Regular expression: ");
				String re = lineInput.readLine();
				if (re.equals(""))
					re = oldRe;
				if (re.equals("quit"))
					return;
				oldRe = re;
				System.out.print("String: ");
				String line = lineInput.readLine();
				System.out.println(Pattern.matches(re, line));
			}
		} catch (Exception e) {

		}
	}

	public static void doMatchingWithGroups() {
		try {
			BufferedReader lineInput = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				System.out.print("Regular expression: ");
				String re = lineInput.readLine();
				if (re.equals(""))
					return;

				Pattern pattern = Pattern.compile(re);
				while (true) {
					System.out.print("String: ");
					String line = lineInput.readLine();
					if (line.equals(""))
						break;

					Matcher m = pattern.matcher(line);
					if (m.matches()) { // see also m.find
						System.out.println(line + " matches pattern");
						for (int i = 0; i <= m.groupCount(); i++) {
							System.out.println("m.group(" + i + ")=" + m.group(i));
						}
					} else {
						System.out.println(line + " does not match pattern.");
					}
				}
			}
		} catch (Exception e) {

		}
	}

	public static void main(String[] args) {

		// doMatching();
		// doMatchingWithGroups();

		// You can do an individual match in one easy line:
		System.out.println(Pattern.matches("a*b", "aaaaab"));

		// Notice that it automatically anchors
		// That is, it is equivalent to ^a*b$
		System.out.println(Pattern.matches("a*b", "baaaaab"));
		System.out.println();

		// If you never reuse the same pattern, this is fine.
		// As in this method:
		// doMatching();

		// An example of pattern matching, with a compiled pattern and using groups
		// doMatchingWithGroups();

		// But if you plan to reuse a pattern, it's more efficient
		// to let Java build the matching infrastructure once and
		// reuse it for each match against that pattern.
		Pattern p = Pattern.compile("CSC[0-9][0-9][0-9]H5(F|S)");
		Matcher m = p.matcher("CSC207H5F");
		System.out.println("Does CSC207H5F match " + p + " ?");
		System.out.println(m.matches());

		// Here we reuse that (under the hood) infrastructure.
		System.out.println("Does CSC199H1Y match " + p + " ?");
		System.out.println(p.matcher("CSC199H1Y").matches() + "\n");

		// The matcher has other methods that let you find out
		// which substrings matched with which "capturing group"
		// of the pattern. Each capturing groups begins with a
		// left bracket. The capturing groups are numbered from 0,
		// and group 0 is the whole pattern.

		// Here I add more brackets to the pattern.
		// This will allow us to capture the group of characters
		// that is the course number.
		// (Exercise: rewrite the pattern to be more concise)
		p = Pattern.compile("CSC([0-9][0-9][0-9])H1(F|S)");
		m = p.matcher("CSC207H1S");
		System.out.println(m.matches());
		System.out.println("Group 0: " + m.group(0)); // the entire string
		System.out.println("Group 1: " + m.group(1)); // the first group: 207
		System.out.println("Group 2: " + m.group(2)); // the second group: S

		// Using a backreference.
		p = Pattern.compile("(\\d\\d\\d)ABC\\1");
		m = p.matcher("123ABC123");
		System.out.println(m.matches());
		m = p.matcher("123ABC456");
		System.out.println(m.matches());

		// find() and match()
		p = Pattern.compile("\\d\\d\\d");
		m = p.matcher("a123b");
		System.out.println(m.find()); // find a substring that matches
		System.out.println(m.matches()); // matches the entire string
	}
}