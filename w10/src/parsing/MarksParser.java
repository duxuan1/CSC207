package parsing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// See http://download.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html

public class MarksParser {
	
	private String basePath;
	private TreeMap<String, Student> utoridToStudent = new TreeMap<String, Student>();
	private int lineNumber = 0; // The line number of the file we are currently parsing

	public static void main(String[] args) {
		MarksParser mp = new MarksParser("./");
		mp.parse();
	}

	public MarksParser(String basePath) {
		this.basePath = basePath;
	}
	
	public void parse() {
		try {
			this.parseUtorids();
			this.parseMarks();
			this.parseMarks2();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void parseUtorids() throws IOException {
		
		BufferedReader inputStream = null;
		try {
			inputStream = new BufferedReader(new FileReader(basePath + "utorids.txt"));

			String l;
			while ((l = inputStream.readLine()) != null) {
				String[] s = l.split(",");
				utoridToStudent.put(s[1], new Student(s[0], s[1], s[4], s[3]));
				System.out.println(utoridToStudent.get(s[1]));
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

	public void parseMarks() throws IOException {
		// See java.util.regex.Pattern, java.util.regex.Matcher

		BufferedReader inputStream = null;
		try {
			Pattern p = Pattern.compile("^(.*)/JugPuzzleGame/src/JugPuzzleGUIController\\.java$");

			inputStream = new BufferedReader(new FileReader(basePath + "all.txt"));

			String l;
			while ((l = inputStream.readLine()) != null) {
				Matcher m = p.matcher(l);
				if (m.matches()) {
					System.out.println(l);
					System.out.println("Groupcount: " + m.groupCount());
					System.out.println(m.group(1) + " ");
				}
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

	public void parseMarks2() throws IOException {
		// See java.util.regex.Pattern, java.util.regex.Matcher

		BufferedReader inputStream = null;
		try {
			Pattern pColons = Pattern.compile("^:{14}$");
			Pattern pStartMarksLine = Pattern.compile("MARKS For Assignment 1, Part 2");
			Pattern pGUIMarks = Pattern.compile("^GUI:\\s*(\\d(\\.\\d)?)/5\\s*$");
			Pattern pCodeMarks = Pattern.compile("^CODE:\\s*(\\d(\\.\\d)?)/5\\s*$");

			Pattern pEndMarksLine = Pattern.compile("^END MARKS$");
			Pattern pUtorid = Pattern.compile("^(.*)/JugPuzzleGame/src/JugPuzzleGUIController\\.java$");

			inputStream = new BufferedReader(new FileReader(basePath + "all.txt"));

			int state = 0;
			// State 0 is before ":::::::"

			Matcher m;
			String l, utorid = "";
			float guiMark = 0, codeMark = 0;
			lineNumber = 0;
			while ((l = inputStream.readLine()) != null) {
				lineNumber++;
				switch (state) {
				case 0: // state before :::::::
					m = pColons.matcher(l);
					if (m.matches()) {
						utorid = "";
						guiMark = 0;
						codeMark = 0;
						state = 1;
					}
					break;
				case 1: // after reading the opening :::::::
					m = pUtorid.matcher(l);
					if (m.matches()) {
						utorid = m.group(1);
						state = 2;
					} else {
						error("Expecting utorid line");
						return;
					}
					break;
				case 2: // after reading the utorid
					m = pColons.matcher(l);
					if (m.matches())
						state = 3;
					else {
						error("Expecting colons");
						return;
					}
					break;
				case 3: // after reading the :::: below the utorid line
					m = pStartMarksLine.matcher(l);
					if (m.matches()) {
						state = 4;
						break;
					}
					m = pColons.matcher(l);
					if (m.matches()) {
						error("Expecting start marks line");
						return;
					}
					break;
				case 4: // after reading the MARKS FOR ...
					m = pGUIMarks.matcher(l);
					if (m.matches()) {
						guiMark = Float.parseFloat(m.group(1));
						state = 5;
					} else {
						error("Expecting GUI mark");
						return;
					}
					break;
				case 5: // after reading the GUI mark
					m = pCodeMarks.matcher(l);
					if (m.matches()) {
						codeMark = Float.parseFloat(m.group(1));
						state = 6;
					} else {
						error("Expecting code marks");
						return;
					}
					break;
				case 6: // after reading the CODE mark
					m = pEndMarksLine.matcher(l);
					if (m.matches()) {
						this.utoridToStudent.get(utorid).setGuiMark(guiMark);
						this.utoridToStudent.get(utorid).setCodeMark(codeMark);

						state = 0;
					}
				}
			}
			
			// Checks at the end of reading
			if (state != 0) {
				error("Expected end of file");
			} else {
				for (String s : this.utoridToStudent.keySet()) {
					System.out.println(utoridToStudent.get(s));
				}
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

	private void error(String mesg) {
		System.out.println("Error:(" + lineNumber + ") " + mesg);
	}
}
