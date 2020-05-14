/**
 * A Balloon to which we can add air.
 * A Balloon has an amount and capacity. Amount is at most capacity and at least 0.
 * A Balloon is popped if we add air that is beyond the capacity.
 * We can add air to the balloon only if it is not popped. 
 */
public class Balloon {

	// the instance variables get declared outside of any method
	private int amount;
	private int capacity;
	private boolean popped;
	private String color;
	
	/** 
	 * the constructor has the same name as the class
	 * no return type; not even void
	 * 
	 * @param color		The colour of the Balloon
	 */
	public Balloon(String color) {
		// "this" is like Python's "self"
		this.amount = 0;
		this.capacity = 100;
		this.popped = false;
		this.color = color;
	}
	
	/** 
	 * Another constructor with a different type signature
	 * 
	 * @param color		The color of the Balloon
	 * @param capacity	The capacity of the Balloon
	 */	
	public Balloon(String color, int capacity) {
		// "this" is like Python's "self"
		this.amount = 0;
		this.capacity = capacity;
		this.popped = false;
		this.color = color;
	}
	
	/**
	 * Get the colour of the Balloon
	 * 
	 * @return		The color of the balloon
	 */
	public String getColor() {
		return this.color;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	// private because user is not supposed to set the amount.
	private void setAmount(int amnt) {
		this.amount = amnt;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public boolean isPopped() {
		return this.popped;
	}
	
	// private because user is not supposed to call pop() directly
	private void pop() {
		this.setAmount(0);
		this.capacity = 0;
		this.popped = true;
		System.out.println("BOOM!");
	}

	public void addAir(int a) {
		if (!this.isPopped() && a > 0) {
			this.amount += a;
			if (this.amount > this.capacity) {
				this.pop();
			}
		}
	}
	
	public String toString() {
		// complete this
		String s = "Colour: " + this.color + 
				 ", Amount: " + this.amount + 
				 ", Capacity: " + this.capacity + 
				 ", Popped: " + this.popped;
		return s;
	}
}
