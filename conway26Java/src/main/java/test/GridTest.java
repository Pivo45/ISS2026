package main.java.test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.conway.domain.*;




public class GridTest {

	private IGrid grid = new Grid();
	private Cell cell = new Cell();
	
	@Test
	public void testSetGetCell() {
		grid.setCell(cell,0,0);
		Cell c = grid.getCell(0, 0);
		assertEquals(c.isAlive(), cell.isAlive());
	}
	

}
