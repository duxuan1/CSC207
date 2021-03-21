import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class BalloonTest {
	
	// This Before method is called before EACH SINGLE test case
	@Before
	public void setUp() {
		System.out.println("Before testing, calling setup()");
	}

	@Test
	public void testAddAirNoPop() {
		Balloon b = new Balloon("red");
		b.addAir(10);
		assertEquals("After inflating by 10 amt", 10, b.getAmount());
		assertEquals("After inflating by 10 capacity", 100, b.getCapacity());
		assertEquals("After inflating by 10 isPopped", false, b.isPopped());
		
		b = new Balloon("blue");
		b.addAir(0);
		assertEquals("After inflating by 0 amt", 0, b.getAmount());
		assertEquals("After inflating by 0 capacity", 100, b.getCapacity());
		assertEquals("After inflating by 0 isPopped", false, b.isPopped());
	}

	@Test
	public void testAddAirAlmostPop() {
		Balloon b=new Balloon("red");
		b.addAir(100);
		assertEquals("After inflating by 100 amt", 100, b.getAmount());
		assertEquals("After inflating by 100 capacity", 100, b.getCapacity());
		assertEquals("After inflating by 100 isPopped", false, b.isPopped());
		
		b = new Balloon("red", 55);
		b.addAir(55);
		assertEquals("After inflating by 55 amt", 55, b.getAmount());
		assertEquals("After inflating by 55 capacity", 55, b.getCapacity());
		assertEquals("After inflating by 55 isPopped", false, b.isPopped());
	}
	
	@Test
	public void testAddAirPop() {
		Balloon b = new Balloon("red");
		b.addAir(101);
		assertEquals("After inflating by 101 amt", 0, b.getAmount());
		assertEquals("After inflating by 101 capacity", 0, b.getCapacity());
		assertEquals("After inflating by 101 isPopped", true, b.isPopped());
	}
	@After
	public void cleanUp() {
		System.out.println("After testing: calling cleanUp()");
	}
}
