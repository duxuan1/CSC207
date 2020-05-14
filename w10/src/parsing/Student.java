package parsing;

public class Student {
	
	private String studentNumber, utorid, firstName, lastName;
	float guiMark, codeMark;

	public Student(String studentNumber, String utorid, String firstName, String lastName) {
		this.studentNumber = studentNumber;
		this.utorid = utorid;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void setGuiMark(float guiMark) {
		this.guiMark = guiMark;
	}

	public void setCodeMark(float codeMark) {
		this.codeMark = codeMark;
	}

	public String toString() {
		return this.studentNumber + " " + this.utorid + " " + this.lastName 
				+ "," + this.firstName + " " + this.guiMark + " " + this.codeMark;
	}
}