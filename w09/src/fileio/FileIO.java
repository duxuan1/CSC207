package fileio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FileIO {

	public static void consoleReadCharacters() {
		// System class System.in, System.out
		// https://docs.oracle.com/javase/8/docs/api/
		char[] c = new char[10];

		try {
			for (int i = 0; i < c.length; i++) {
				c[i] = (char) System.in.read(); // System.in is an InputStream
			}
		} catch (IOException e) {
			System.out.println(e);
		}

		for (int i = 0; i < c.length; i++) {
			System.out.println("c[" + i + "]=" + c[i]);
		}
	}

	public static void consoleReadLines() {

		// Would prefer to read a line at a time...
		BufferedReader lineInput = new BufferedReader(new InputStreamReader(System.in));
		String line;
		try {
			while ((line = lineInput.readLine()) != null) {
				System.out.println("line = " + line + ", size=" + line.length());
			}
		} catch (IOException e) {
			System.out.println(e);
		}

		// Alternatively, can use a scanner like we've already known
		// Scanner sc = new Scanner(System.in);
	}

	public static void fileReadLines() {
		// read words and count the number of lines in the file
		try {
			FileReader fr = new FileReader("words.txt");
			BufferedReader lineInput = new BufferedReader(fr);
			String line;
			int count = 0;
			while ((line = lineInput.readLine()) != null) {
				if (line.startsWith("q")) {
					System.out.println(line);
					count++;
				}
			}
			fr.close();
			System.out.println(count);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e1) {
			System.out.println(e1);
		}
		// read words and print them all out
	}

	public static void fileScanLines() {

		try {
			BufferedReader in = new BufferedReader(new FileReader("words.txt"));
			Scanner s = new Scanner(in);
			while (s.hasNextLine()) {
				String line = s.nextLine();
				if (line.startsWith("ab")) {
					System.out.println(line);
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}

	public static void fileReadWrite() throws IOException {

		FileReader in = null;
		FileWriter out = null;

		try {
			in = new FileReader("words.txt");
			out = new FileWriter("words-copy.txt");

			int c;
			System.out.println("Copying...");
			while ((c = in.read()) != -1) {
				if (c == 'o') {
					out.write("***");
				}
				else {
					out.write(c);
				}
			}
			System.out.println("Done!");
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

	public static void main(String[] args) {
//		 consoleReadCharacters();
//		 consoleReadLines();
//		 fileReadLines();
//		 fileScanLines();
		try {
			fileReadWrite();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
