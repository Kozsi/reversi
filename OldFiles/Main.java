package reversi;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * A jatek torzset tartalmazo osztaly.
 * 
 * @author kozsi
 *
 */
public class Main {

	/**
	 * Logolásra használt eszköz.
	 */

	private static Logger logger = (Logger) LoggerFactory.getLogger(Main.class);

	/**
	 * A jatek torzset tartalmazo metodus.
	 * 
	 * @param args
	 *            jelenleg nincs hasznalatban
	 */

	public static void main(String[] args) {
		/* Variables */
		Core c = new Core();
		int player = 1, moved = 0, done = 0;
		String[] s = {};
		int[] coordinates = new int[2];
		Scanner sc;
		/* Game */
		while (true) {

			done = 0;
			c.currentState();
			System.out.println("Pieces: ");
			c.getScore();

			if (c.isItEnd() == 1) {
				break;
			}

			if (c.isShearPossible(player)) {
				System.out.println("Player 1 is next!");
				while (done != 1) {

					int right = 0;
					while (s.length < 2 || right == 0) {
						right = 0;
						s = new String[0];
						System.out.println("Kerem adja meg a koordinatakat szokozokkel elvalasztva!");
						sc = new Scanner(System.in);
						s = sc.nextLine().split(" ");
						if (s.length >= 2) {
							if (Integer.parseInt(s[0]) >= 1 && Integer.parseInt(s[1]) <= 8) {
								right = 1;
							} else {
								logger.info("A " + player + "jatekos tul nagy koordinatakat adott meg!");
								System.out.println("Tul nagy koordinatak!!");
							}
						}
						if (s.length < 2) {
							logger.info("A %d. jatekos tul keves koordinatat adott meg!", player);
							System.out.println("Tul keves koordinata!!");
						}
					}

					if (c.ableToAtoi(s, coordinates[0], coordinates[1]) == 1) {
						if ((coordinates[0] = Integer.parseInt(s[0])) == 0) {
							coordinates[0] = 0;
						} else {
							coordinates[0] = Integer.parseInt(s[0]) - 1;
						}
						if ((coordinates[1] = Integer.parseInt(s[1])) == 0) {
							coordinates[1] = 0;
						} else {
							coordinates[1] = Integer.parseInt(s[1]) - 1;
						}

						moved = c.processShears(1, coordinates[0], coordinates[1]);
						if (moved > 0) {
							c.processShears(1, coordinates[0], coordinates[1]);
							done = 1;
						} else {
							logger.info("A" + player + "jatekos hibasan lepett!");
							System.out.println("Wrong Move!");
						}
					}

				}

				player = 2;

			} else {
				player = 2;
			}

			done = 0;
			c.currentState();
			System.out.println("Pieces: ");
			c.getScore();
			if (c.isItEnd() == 1) {
				break;
			}

			if (c.isShearPossible(player)) {
				System.out.println("Player 2 is next!");
				while (done != 1) {

					int right = 0;
					while (s.length < 2 || right == 0) {
						right = 0;
						s = new String[0];
						System.out.println("Kerem adja meg a koordinatakat szokozokkel elvalasztva!");
						sc = new Scanner(System.in);
						s = sc.nextLine().split(" ");
						if (s.length >= 2) {
							if (Integer.parseInt(s[0]) >= 1 && Integer.parseInt(s[1]) <= 8) {
								right = 1;
							} else {
								logger.info("A" + player + "jatekos tul nagy koordinatakat adott meg!");
								System.out.println("Tul nagy koordinatak!!");
							}
						}
						if (s.length < 2) {
							logger.info("A" + player + "jatekos tul keves koordinatat adott meg!");
							System.out.println("Tul keves koordinata!!");
						}
					}

					if (c.ableToAtoi(s, coordinates[0], coordinates[1]) == 1) {
						if ((coordinates[0] = Integer.parseInt(s[0])) == 0) {
							coordinates[0] = 0;
						} else {
							coordinates[0] = Integer.parseInt(s[0]) - 1;
						}
						if ((coordinates[1] = Integer.parseInt(s[1])) == 0) {
							coordinates[1] = 0;
						} else {
							coordinates[1] = Integer.parseInt(s[1]) - 1;
						}

						moved = c.processShears(2, coordinates[0], coordinates[1]);
						if (moved > 0) {
							c.processShears(2, coordinates[0], coordinates[1]);
							done = 1;
						} else {
							logger.info("A" + player + "jatekos hibasan lepett!");
							System.out.println("Wrong Move!");
						}
					}

				}
				player = 1;
			} else {
				player = 1;
			}

		}
		if (c.getBlack() > c.getWhite()) {
			player = 1;
		} else {
			player = 2;
		}
		System.out.println("End of the Game! The winner is Player: " + player);

	}

}
