package main.java.conway.domain;

import java.util.HashMap;
import java.util.Map;

public class Grid implements IGrid{

	private int rows;
	private int columns;
	private Cell[][] celle;


	private void initGrid() {
		// Crea la matrice
		this.celle = new Cell[this.rows][this.columns];

		// Inizializza ogni cella a "morta" (false)
		for (int r = 0; r < this.rows; r++) {
			for (int c = 0; c < this.columns; c++) {
				this.celle[r][c] = new Cell(false);
			}
		}
	}


	public Grid() {
		this.rows = 10;
		this.columns = 10;
		initGrid();
	}
	public Grid(int row, int column) {
		this.columns = column;
		this.rows = row;
		initGrid();
	}

	public Grid(boolean[][] griglia) {
		this.rows = griglia.length;
		this.columns = griglia[0].length;
		this.celle = new Cell[griglia.length][griglia[0].length];

		for(int r = 0; r < griglia.length; r++) {
			for(int c = 0; c < griglia[0].length; c++) {
				this.celle[r][c] = new Cell(griglia[r][c]);
			}
		}
	}

	private boolean isOutOfBound(int r, int c) {
		return (r >= this.rows || c >= this.columns || r < 0 || c < 0);
	}

	@Override
	public Cell getCell(int row, int column) {
		if(isOutOfBound(row,column)) {
			return new Cell(false);
		}
		return this.celle[row][column];
	}

	@Override
	public boolean setCell(Cell cell, int row, int column) {
		if(isOutOfBound(row,column)) {
			return false;
		}
		this.celle[row][column] = cell.clone();
		return true;

	}


	public boolean[][] getGridAsArray() {
		boolean[][] result = new boolean[this.rows][this.columns];
		for (int r = 0; r < rows; r++) {
		        for (int c = 0; c < columns; c++) {
		            result[r][c] = getCell(r, c).isAlive();
		        }
		    }
		    return result;
		}
}
