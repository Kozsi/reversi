package reversi;

import static org.junit.Assert.*;
import org.junit.Test;

public class CoreTest {

	public Core c = new Core();

	@Test
	public void testisItEnd() {

		assertEquals(0, c.isItEnd());
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				c.getTable()[i][j] = 1;
			}
		}
		
		assertEquals(1, c.isItEnd());
	}

	@Test
	public void testcheckMove() {
		assertEquals(1, c.checkMove(1, 2, 4, 1, 0));
		assertEquals(1, c.checkMove(1, 3, 5, 0, -1));
		assertEquals(0, c.checkMove(1, 4, 3, 0, 0));
		assertEquals(0, c.checkMove(1, 3, 3, 0, 0));
	}

	@Test
	public void testPlace() {
		assertEquals(1, c.place(1, 3, 5, 0, -1));
		assertEquals(1, c.place(2, 2, 5, 1, -1));
		assertEquals(0, c.place(1, 1, 5, 0, -1));
		assertEquals(0, c.place(2, 1, 6, 1, 1));
		assertEquals(0, c.place(1, 3, 4, 0, -1));
		assertEquals(0, c.place(2, 3, 3, 1, 1));
		assertEquals(0, c.place(1, 3, 2, 0, 0));
		assertEquals(0, c.place(2, 3, 4, 0, 0));
		
	}

	@Test
	public void testProcessShears() {
		assertEquals(1, c.processShears(1, 3, 5));
		//c.place(1, x, y, vector1, vector2)
		assertEquals(1, c.processShears(2, 2, 3));
	}

	@Test
	public void testgetShears() {
		assertEquals(1, c.getShears(2, 2, 3));
		assertEquals(1, c.getShears(1, 3, 5));
		assertEquals(0, c.getShears(2, 2, 5));
		assertEquals(0, c.getShears(1, 5, 5));
	}
	int x, y;
	
	@Test
	public void testableToAtoi() {
		String[] seged = { "3", "3" };
		assertEquals(1, c.ableToAtoi(seged, x, y));
		seged[0] = "Radiohead";
		seged[1] = "Idioteque";
		assertEquals(0, c.ableToAtoi(seged, x, y));
	}

	@Test
	public void testisShearPossible() {
		assertTrue(c.isShearPossible(1));
		assertTrue(c.isShearPossible(2));
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				c.getTable()[i][j] = 1;
			}
		}
		assertFalse(c.isShearPossible(1));
		assertFalse(c.isShearPossible(2));
		
	}

}
