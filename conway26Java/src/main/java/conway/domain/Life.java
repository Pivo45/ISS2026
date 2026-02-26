package main.java.conway.domain;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Life implements LifeInterface{
	private final int rows;
    private final int cols;
    
    // Due matrici distinte
    private Grid gridA;
    private Grid gridB;
    
 // Un riferimento che punta sempre alla griglia che contiene lo stato attuale
    private Grid currentGrid;
    private Grid nextGrid;
    
   public static LifeInterface CreateGameRules() {
	   return new Life(5, 5); 
	   // Dimensioni di default, possono essere 
	   //lette da un file di configurazione o passate come parametri
   }

    // Costruttore che accetta una griglia pre-configurata (utile per i test)
    public Life(boolean[][] initialGrid) {
    	this.rows = initialGrid.length;
        this.cols = initialGrid[0].length;
        
        // Inizializziamo entrambe le matrici
        this.gridA = new Grid(initialGrid);
        this.gridB = new Grid(rows, cols);
        
        this.currentGrid = gridA;
        this.nextGrid    = gridB;   
    }

    // Costruttore che crea una griglia vuota di dimensioni specifiche
    public Life(int rows, int cols) {
    	this.rows = rows;
        this.cols = cols;
        this.gridA = new Grid(rows, cols);
        this.gridB = new Grid(rows, cols);
        this.currentGrid = gridA;
        this.nextGrid    = gridB;   
    }

    // Calcola la generazione successiva applicando le 4 regole di Conway
    public void nextGeneration() {
    	// Applichiamo le regole leggendo da currentGrid e scrivendo in nextGrid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int neighbors = countNeighborsLive(r, c);
                boolean isAlive = currentGrid.getCell(r, c).isAlive();
                nextGrid.setCell(new Cell(willSurvive(isAlive, neighbors)), r, c);
            }
        }

        // --- IL PING-PONG ---
        // Scambiamo i riferimenti: ciò che era 'next' diventa 'current'
        Grid temp = currentGrid;
        currentGrid      = nextGrid;
        nextGrid         = temp;
        // Nota: non abbiamo creato nuovi oggetti, abbiamo solo spostato i puntatori
    }
    
    private boolean willSurvive(boolean wasAlive, int n) {
        return wasAlive ? (n == 2 || n == 3) : (n == 3);
    }
    
    protected int countNeighborsLive(int row, int col) {
        int count = 0;

        // Loop su tutte le celle nel raggio 1 (3x3)
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {

                // 1) Salta la cella centrale (se stessa)
                if (r == row && c == col) 
                    continue;

                Cell neighbor = this.currentGrid.getCell(r, c);

                // 3) Se è viva, incrementa il conteggio
                if (neighbor.isAlive()) 
                    count++;
            }
        }

        return count;
    }


    // Metodi di utilità per i test
    public Cell getCell(int r, int c) { return currentGrid.getCell(r, c); }
    public void setCell(int r, int c, boolean state) { currentGrid.setCell(new Cell(state), r, c); }
    public Grid getGrid() { return currentGrid; }

	@Override
	public boolean isAlive(int row, int col) {
		return currentGrid.getCell(row, col).isAlive();
	}

	@Override
	public int getRows() {
 		return 0;
	}

	@Override
	public int getCols() {
 		return 0;
	}
	
	//Versione NAIVE
//	private boolean[][] deepCopy(boolean[][] original) {
//	    if (original == null) return null;
//
//	    boolean[][] result = new boolean[original.length][];
//	    for (int i = 0; i < original.length; i++) {
//	        // Creiamo una nuova riga e copiamo i valori della riga originale
//	        result[i] = original[i].clone(); 
//	        // Nota: clone() su un array di primitivi (boolean) è sicuro 
//	        // perché i primitivi vengono copiati per valore.
//	    }
//	    return result;
//	}
	
	
	
	
	public String gridRep( ) {
	    return Arrays.stream(currentGrid.getGridAsArray()) // Stream di boolean[] (le righe)
	        .map(row -> {
	            // Trasformiamo ogni riga in una stringa di . e O
	            StringBuilder sb = new StringBuilder();
	            for (boolean cell : row) {
	                sb.append(cell ? "O " : ". ");
	            }
	            return sb.toString();
	        })
	        .collect(Collectors.joining("\n")); // Uniamo le righe con un a capo
	}
}
