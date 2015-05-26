package reversi;
/**
 * Tablat letrehozo osztaly esa jatek allasat tartalmazza.
 * @author kozsi
 *
 */
public class Table {
	/**
	 * A tabla ertekeit tartalmazo matrix.
	 * @param table egy matrix
	 */
	private int table[][] = new int[8][8];
	/**
	 * A fekete babu ertekei.
	 * @param fekete babu szam
	 */
	private int black;
	/**
	 * A feher babu ertekei.
	 * @param feher babu szam
	 */
	private int white;
	/**
	 * Konstruktor.
	 */
	public Table() {	
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				table[i][j] = 0;
			}
		}
		table[3][3] = 1;
		table[4][4] = 1;
		table[3][4] = 2;
		table[4][3] = 2;
		black = 2;
		white = 2;	
		
	}
	/**
	 * Olyan metodus mely visszadja a fekete babuk erteket.
	 * @return visszadja a fekete babuk erteket
	 */
	public int getBlack() {
		return black;
	}
	/**
	 * Olyan metodus mely visszadja a feher babuk erteket.
	 * @return visszadja a feher babuk erteket
	 */
	public int getWhite() {
		return white;
	}
	/**
	 * Olyan metodus mely beallaitja a fekete babuk erteket.
	 * @param a az adott ertekre allitja a feketet
	 */
	public void setBlack(int a){
		this.black = a;
	}
	/**
	 * Olyan metodus mely beallaitja a feher babuk erteket.
	 * @param a az adott ertekre allitja a feheret
	 * 
	 */
	public void setWhite(int a){
		this.white = a;
	}
	/**
	 * Olyan metodus, mely visszadja a tablat.
	 * 
	 * @return a tablat adja vissza
	 */
	public int[][] getTable() {
		return table;
	}
	
}
