package main.java.conway.domain;

public class Cell implements ICell{

	/* Rappresentazione concreta di una cella*/
	private boolean status;
	
	public Cell() {
		this.status = false;
	}
	
	public Cell(boolean status) {
		this.status = status;
	}
	
	@Override
	public boolean isAlive() {
		return status;
	}

	@Override
	public void setState(boolean state) {
		this.status = state;
		
	}
	
	public Cell clone() {
		return new Cell(this.status);
	}

	
}
