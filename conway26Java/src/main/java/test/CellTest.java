package main.java.test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.conway.domain.Cell;
import main.java.conway.domain.ICell;

public class CellTest {
	
	//simbolo C, rispetta il contratto delle ICell
	private ICell c = new Cell();

	
	 @Test
	public void testCellAlive() {
		c.setState(true);
		boolean r = c.isAlive();
		assertTrue(r);
	}
	 @Test
	 public void testCellDead() {
		 c.setState(false);
		 boolean r = c.isAlive();
		 assertFalse(r);
	 }

}
