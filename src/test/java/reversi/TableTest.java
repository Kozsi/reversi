package reversi;

import static org.junit.Assert.*;
import org.junit.Test;

public class TableTest {

	private Table t = new Table();
	
	
	
	@Test
	public void testGetBlack() {
		
		assertEquals(2, t.getBlack());
	}

	@Test
	public void testGetFeher() {
		
		assertEquals(2, t.getWhite());
	}
	
	@Test
	public void testPalya() {
		
		int tomb[][] = t.getTable();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i == 3 && j == 3) || (i == 4 && j == 4)) {
					assertEquals(1, tomb[i][j]);
				} else {
					if ((i == 3 && j == 4) || (i == 4 && j == 3)) {
						assertEquals(2, tomb[i][j]);
					} else {
						assertEquals(0, tomb[i][j]);
					}
				}
			}
		}
	}

}
