package people;

public class PlayWithPeople {
	
	public static void main(String args[]) {
				
		Person p1 = null;
		System.out.println(p1);
		
		p1 = new Person("Alice", 22);
		System.out.println(p1.getName());
		System.out.println(p1.hello());
		
		Student s1 = new Student("Bob", 24, "1234512345");
		System.out.println(s1.getStudentNumber());
		System.out.println(s1.hello());
		
		Person p2 = s1;  // This is fine, auto-conversion happens from Student to Person
		// p2 is reference that refers to a Student as a Person

		System.out.println(p2.hello()); 
		// the hello() method of the Student class is invoked because the object
		// p2 is referring to is _created_ as a Student.
		// Read "Dynamic Binding" in Lecture 3 slides for more detail.
		
		//System.out.println(p2.getStudentNumber()); // NOT OK
		
		Student s3 = null;
		//s3 = p2;
		s3 = (Student)p2;
		// Programmer who writes this casting: "Trust me, I know what I'm doing. I am 
		// sure that this Person that p2 is referring to is indeed a Student (has 
		// student number and stuff), i.e., I know that the Person living at the address 
		// stored in p2 is a Student; so this casting is safe, nothing will go wrong".
		System.out.println(s3.hello());
		System.out.println(s3.getStudentNumber());
		
		s3 = (Student)p1; 
		// This casting is NOT safe because p1 (Alice) is not a Student; runtime error.
		
		Doctor d1 = new Doctor("Who", 25);
		System.out.println(d1.hello());
	}
}

