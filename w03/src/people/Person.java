package people;
// The above line declares that the Person class is part of the 
// inheritanceDemo package, the full path of the Person class 
// would be inheritanceDemo.Person

/**
 * This docstring will go into the doc generated by Javadoc
 * A Person is the base class of any person type.
 * A Person has a name and an age
 * A Person can say hello using the string returned by the hello() method 
 */
public class Person {
	
	private String name;
	private int age;
	
	/** 
	 * The Person constructor.
	 * The constructor has the same name as the class
	 * no return type; not even void
	 * 
	 * @param name		The name of the Person
	 * @param age		The age of the Person
	 */
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	/** 
	 * Returns the name of the Person
	 * @return		The name of the Person as a String
	 */
	public String getName() {
		return this.name;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public String hello() {
		return "Hello, I am " + this.getName();
	}
}