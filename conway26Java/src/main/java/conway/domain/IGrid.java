package main.java.conway.domain;

public interface IGrid {

	Cell getCell(int row, int column);
	
	boolean setCell(Cell cell,int row, int column);
}