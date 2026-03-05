package main.java.test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.java.conway.domain.Life;
import main.java.conway.domain.LifeInterface;

/**
 * Test per il modello Conway Game of Life
 */
public class LifeTest {

	private LifeInterface life;//= new Life(5,5);

	@Before
	public void setup() {
		System.out.println("LifeTest | setup");
	}

	@After
	public void down() {
		System.out.println("LifeTest | down");
	}


	// -------------------------------------------------------------
	// TEST 1: tutte le celle devono essere morte dopo resetGrids()
	// -------------------------------------------------------------
	@Test
	public void testResetGrids() {
		// imposto qualche cella a viva
		life.setCell(1, 1, true);
		life.setCell(3, 4, true);

		life.resetGrids();

		// ora controllo che TUTTE le celle della griglia siano morte
		for (int r = 0; r < life.getRows(); r++) {
			for (int c = 0; c < life.getCols(); c++) {
				assertFalse("La cella (" + r + "," + c + ") dovrebbe essere morta dopo reset",
						life.isAlive(r, c) );
			}
		}
	}


	// -------------------------------------------------------------
	// TEST 2: nextGeneration() – caso del BLINKER (oscillatore)
	// Colonna verticale (stato iniziale)
	// deve diventare riga orizzontale
	//
	//   . . . . .
	//   . . O . .
	//   . . O . .
	//   . . O . .
	//   . . . . .
	//
	// dopo nextGeneration():
	//
	//   . . . . .
	//   . . . . .
	//   . O O O .
	//   . . . . .
	//   . . . . .
	// -------------------------------------------------------------
	@Test
	public void testBlinkerOscillation() {
		// Stato iniziale: blinker verticale
		life.resetGrids();
		life.setCell(1, 2, true);
		life.setCell(2, 2, true);
		life.setCell(3, 2, true);

		life.nextGeneration();

		// Dopo una generazione → riga orizzontale
		assertTrue(life.isAlive(2, 1));
		assertTrue(life.isAlive(2, 2));
		assertTrue(life.isAlive(2, 3));


		for (int r = 0; r < life.getRows(); r++) {
			for (int c = 0; c < life.getCols(); c++) {
				boolean expectedAlive = (r == 2 && (c == 1 || c == 2 || c == 3));
				assertEquals(expectedAlive, life.isAlive(r, c));
			}
		}
	}

	@Test
	public void testLonelyCellDies() {
		life.resetGrids();
		life.setCell(2, 2, true); // cella isolata viva

		life.nextGeneration();

		assertFalse("Una cella viva isolata deve morire",
				life.isAlive(2, 2));
	}

}