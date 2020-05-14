package people;

public class Doctor extends Person {
	
	public Doctor(String name, int age) {
		super(name, age);
	}
	
	public String getName() {
		return "Dr. " + super.getName();
	}
}