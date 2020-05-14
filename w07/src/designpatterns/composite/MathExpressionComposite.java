package designpatterns.composite;

// A composite expression component is something like (x+y) or (x/y)
// with two operands and an operator
public class MathExpressionComposite implements MathExpressionComponent {

	private MathExpressionComponent left;
	private MathExpressionComponent right;
	private String operator;

	public MathExpressionComposite(MathExpressionComponent left, String operator, MathExpressionComponent right) {

		this.left = left;
		this.right = right;
		this.operator = operator;
	}

	@Override
	public int evaluate() {
		switch (this.operator) {
		case "+":
			return this.left.evaluate() + this.right.evaluate();
		case "-":
			return this.left.evaluate() - this.right.evaluate();
		case "*":
			return this.left.evaluate() * this.right.evaluate();
		case "/":
			return this.left.evaluate() / this.right.evaluate();
		default:
			System.out.println("ERROR: Invalid Operator.");
			return 0;
		}
	}
	
	public String toString() {
		return "(" + this.left.toString() + operator + this.right.toString() + ")";
	}
}
