package reversi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kozslai Gergo
 *
 *         A jatek mukodesehez szukeges metodusokat tartalmazo osztaly.
 */

public class Core extends Table {
	/**
	 * Osztaly konstruktor.
	 */
	public Core() {
		super();
	}

	/**
	 * Vissza ter a jelenlegi allasal.
	 */
	public final void getScore() {
		System.out.println(" Black pieces: " + getBlack() + " White pieces: " + getWhite());
	}

	/**
	 * Logolásra használt eszköz.
	 */

	private static Logger logger = (Logger) LoggerFactory.getLogger(Core.class);

	/**
	 * Levizsgalja hogy a tabla betelt e.
	 *
	 * @return hogy vege van e
	 */
	public int isItEnd() {
		int vege = 1;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (getTable()[i][j] == 0)
					vege = 0;
			}
		}
		if (vege == 0) {
			logger.info("A jatek folytatodik, a tablan meg van szabad hely!");
		} else
			logger.info("A jateknak vege, a tabla betelt!");
		return vege;
	}

	/**
	 * Levizsgalja, hogy szabalyos e a lepes.
	 * 
	 * @param currentPlayer
	 *            a jelenlegi jatekos erteke
	 * @param x
	 *            sor erteke
	 * @param y
	 *            oszlop erteke
	 * @param vector1
	 *            sor iranyaba lep
	 * @param vector2
	 *            oszlop iranyaba lep
	 * @return a lehetseges lepesek szama
	 */
	public int checkMove(int currentPlayer, int x, int y, int vector1, int vector2) {
		if (getTable()[x][y] != 0) {
			return 0;
		}

		x += vector1;
		y += vector2;
		int oppositePlayer = 0, db = 0;
		if (currentPlayer == 2) {
			oppositePlayer = 1;
		} else {
			oppositePlayer = 2;
		}
		while ((x < 8) && (x >= 0) && (y < 8) && (y >= 0)) {
			if (getTable()[x][y] != oppositePlayer)
				break;

			x += vector1;
			y += vector2;
			db++;
		}
		if ((db != 0) && (x < 8) && (x >= 0) && (y < 8) && (y >= 0) && (getTable()[x][y] == currentPlayer)) {
			return db;
		}
		return 0;
	}

	/**
	 * Lerakja a megadott koordinatakra a jatekos babujat, ha szabalyos a lepes,
	 * es az olloban levo babukat megforditja.
	 * 
	 * @param currentPlayer
	 *            a jelenlegi jatekos erteke
	 * @param x
	 *            sor erteke
	 * @param y
	 *            oszlop erteke
	 * @param vector1
	 *            sor iranyaba lep
	 * @param vector2
	 *            oszlop iranyaba lep
	 * @return szabalyos-e a lepes, vagy sem
	 */

	public int place(int currentPlayer, int x, int y, int vector1, int vector2) {
		if (getTable()[x][y] != 0) {
			return 0;
		}
		x += vector1;
		y += vector2;
		int oppositePlayer = 0, db = 0;
		if (currentPlayer == 2) {
			oppositePlayer = 1;
		} else {
			oppositePlayer = 2;
		}
		while ((x < 8) && (x >= 0) && (y < 8) && (y >= 0)) {
			if (getTable()[x][y] != oppositePlayer) {
				break;
			}
			x += vector1;
			y += vector2;
			db++;
		}
		if ((db != 0) && (x < 8) && (x >= 0) && (y < 8) && (y >= 0) && (getTable()[x][y] == currentPlayer)) {
			for (int j = 1; j <= db; j++) {
				x -= vector1;
				y -= vector2;
				flip(currentPlayer, x, y);

			}
			return 1;
		}
		return 0;

	}

	/**
	 * Megfordítja az adott koordinátán lévő bábut.
	 * 
	 * @param player
	 *            a jatekos
	 * @param x
	 *            a tablan levo sort jeloli
	 * @param y
	 *            a tablan levo oszlopot jeloli
	 */
	public void flip(int player, int x, int y) {
		if (getTable()[x][y] != 0) {
			if (player == 2) {
				setWhite(getWhite() + 1);
				setBlack(getBlack() - 1);
			} else {
				setWhite(getWhite() - 1);
				setBlack(getBlack() + 1);
			}
		} else {
			if (player == 1) {
				setBlack(getBlack() + 1);
			} else {
				setWhite(getWhite() + 1);
			}
		}
		getTable()[x][y] = player;
	}

	/**
	 * Levizsgalja, hogy lehetseges-e ollot vegrehajtani.
	 * 
	 * @param player
	 *            az adott jatekos
	 * @param x
	 *            a tablan levo sort jeloli
	 * @param y
	 *            a tablan levo oszlopot jeloli
	 * @return van-e ollo vagy nincs
	 */
	public int getShears(int player, int x, int y) {
		int vektor1 = -1;
		int vektor2 = -1;
		for (int i = 0; i < 8; i++) {

			if (checkMove(player, x, y, vektor1, vektor2) == 1) {
				logger.debug("Talalhato szabalyos lepes!");
				return 1;
			}
			if (vektor1 == -1 && vektor2 != 1) {
				vektor2 += 1;
			} // 2 -1 0, -1 1
			else if (vektor1 != 1 && vektor2 == 1) {
				vektor1 += 1;
			} // 2 0 1, 1 1
			else if (vektor1 == 1 && vektor2 != -1) {
				vektor2 -= 1;
			} // 2 1 0, 1 -1
			else if (vektor1 == 1 && vektor2 == -1) {
				vektor1 -= 1;
			} // 1 0 -1
		}
		// logger.info("Nincs elerheto szabalyos lepes!");
		return 0;
	}

	/**
	 * Olyan metodus, mely azt vizsgalja, hogy lehetseges-e, hogy az adott
	 * jatekos ollot rakjon le.
	 * 
	 * @param player
	 *            az adott jatekos
	 * @return logikai ertek, mely megadja, hogy lehetseges-e vagy sem
	 */
	public boolean isShearPossible(int player) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((getTable()[i][j] == 0) && getShears(player, i, j) == 1) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Vegrehajtja az ollokat.
	 * 
	 * @param player
	 *            az adott jatekos
	 * @param x
	 *            a tablan levo sort jeloli
	 * @param y
	 *            a tablan levo oszlopot jeloli
	 * @return hany darabot forditott meg
	 */
	public int processShears(int player, int x, int y) {
		int pieces = 0;
		int vektor1 = -1;
		int vektor2 = -1;
		for (int i = 0; i < 8; i++) {
			// 1 -1 -1
			if (place(player, x, y, vektor1, vektor2) == 1)
				pieces++;

			if (vektor1 == -1 && vektor2 != 1) {
				vektor2 += 1;
			} // 2 -1 0, -1 1
			else if (vektor1 != 1 && vektor2 == 1) {
				vektor1 += 1;
			} // 2 0 1, 1 1
			else if (vektor1 == 1 && vektor2 != -1) {
				vektor2 -= 1;
			} // 2 1 0, 1 -1
			else if (vektor1 == 1 && vektor2 == -1) {
				vektor1 -= 1;
			} // 1 0 -1
		}
		if (pieces != 0) {
			getTable()[x][y] = player;
			if (player == 1) {
				setBlack(getBlack() + 1);
			} else {
				setWhite(getWhite() + 1);
			}
		}
		logger.debug("Megfordult babuk szama: " + pieces);
		return pieces;

	}

	/**
	 * Levizsgalja az aktualis allast es ki is irja a tablat.
	 */
	public void currentState() {
		System.out.println("Current table");
		System.out.println("===========================");
		int i = 0;
		System.out.print("#  ");
		while (i < 8) {
			System.out.printf("%d  ", i + 1);
			i++;
		}
		System.out.println();
		i = 0;
		while (i < 8) {
			int j = 0;
			System.out.print(i + 1 + "  ");
			while (j < 8) {
				System.out.print(getTable()[i][j] + "  ");
				j++;
			}
			System.out.println();
			i++;
		}
		System.out.println("===========================");

	}

	/**
	 * Lehetseges-e a szam szovegge alakitasa.
	 * 
	 * @param s
	 *            a szoveg
	 * @param x
	 *            a szovegtomb elso eleme
	 * @param y
	 *            a szovegtomb masodik eleme
	 * @return lehetseges-e vagy sem az atalakitas
	 */
	public int ableToAtoi(String[] s, int x, int y) {
		int jo = 0;
		try {
			x = Integer.parseInt(s[0]);
			y = Integer.parseInt(s[1]);
			if (x > 0 && x <= 8 && y > 0 && y <= 8) {
				jo = 1;
			}

		} catch (NumberFormatException ex) {
			logger.debug("Hiba az atalakitas soran!");
			return 0;
		}
		if (jo == 1) {
			if (x != 0) {
				x -= 1;
			}
			if (y != 0) {
				y -= 1;
			}
			logger.debug("Szabalyos atalakitas!");
			return 1;
		}
		return 0;
	}

}
