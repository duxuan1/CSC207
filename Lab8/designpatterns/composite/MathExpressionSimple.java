package designpatterns.composite;

// A simple expression component is simply a single value
// Such a component is a leaf node in the expression tree
public class MathExpressionSimple implements MathExpressionComponent {

	private int value = 0;

	public MathExpressionSimple(int value) {
		this.value = value;
	}

	@Override
	public int evaluate() {
		return this.value;
	}

	public String toString() {
		return String.valueOf(this.value);
	}
}
