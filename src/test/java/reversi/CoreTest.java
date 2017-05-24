package reversi;

import static org.junit.Assert.*;
import org.junit.Test;

public class CoreTest {

	public Core c = new Core();

	@Test
	public void testisItEnd() {

		assertEquals(0, c.isItEnd());
	}

	@Test
	public void testcheckMove() {
		assertEquals(1, c.checkMove(1, 2, 4, 1, 0));
	}

	@Test
	public void testPlace() {
		assertEquals(1, c.place(1, 3, 5, 0, -1));
	}

	@Test
	public void testProcessShears() {
		assertEquals(1, c.processShears(1, 3, 5));
	}

	@Test
	public void testgetShears() {
		assertEquals(1, c.getShears(2, 2, 3));
	}

	String[] seged = { "3", "3" };
	int x, y;

	@Test
	public void testableToAtoi() {
		assertEquals(1, c.ableToAtoi(seged, x, y));
	}

}
