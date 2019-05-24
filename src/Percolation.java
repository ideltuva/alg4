import edu.princeton.cs.algs4.StdOut;

public class Percolation {

	private boolean[][] grid;
	
	private int N;
	
    /**
     * Creating n-by-n grid, all sites blocked (false)
     * @param  n the number of sites
     * @throws IllegalArgumentException if {@code n < 0}
     */	
	
	public Percolation(int n) {
		if (n < 0) throw new IllegalArgumentException("Grid cannot be negative");
		grid = new boolean[n][n];
		N = n;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				grid[i][j]= false;
			}
		}
	}
	
	// validates whether row and col indexes are valid or not
	private void validate(int row, int col) {
		if ((row < 0 || row > N) || (col < 0 || col > N)) 
			throw new IllegalArgumentException("row or col index " + row + ", " + col + " is not between 1 and " + N); 	
	}
	
	public void open(int row, int col) { // open site (row, col) if it is not open already
		validate(row, col);
		if (!grid[row-1][col-1]) grid[row-1][col-1]=true;
	}
	
	/**
	 * @param row, column of the site
	 * @return dhecks whether the site is open or not
	 */
	
	public boolean isOpen(int row, int col) { // is site (row, col) open?
		validate(row, col);
		return grid[row-1][col-1];
	}
	
	/**
	 * @param row, column of the site
	 * @return is a given site full (connected to the bottom)
	 */
	
	public boolean isFull(int row, int col) { // is site (row, col) full?
		validate(row, col);
		boolean[][] gridOut = new boolean[N][N];
		isFull(gridOut, row, col);
		for (int j = 0; j < N; j++) {
			if (gridOut[N-1][j]) return true;		
		}
		
		return false;
	}
	
	private void isFull(boolean[][] gridOut, int row, int col) {
		
		if ((row < 0 || row > N) || (col < 0 || col > N)) return;		
		if (!isOpen(row, col)) return; // not an open site
		if (gridOut[row-1][col-1]) return; // already open
		
		gridOut[row-1][col-1] = true;
		
		//isFull(gridOut, row - 1, col); // down
		isFull(gridOut, row + 1, col); // up
		//isFull(gridOut, row, col - 1); // left
		isFull(gridOut, row, col + 1); // right
	}
	
	/**
	 * 
	 * @return returns the number of open sites
	 */
	
	public int numberOfOpenSites() {
		int openSiteCnt = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(isOpen(i+1, j+1)) openSiteCnt++; 
			}
		}
		
		return openSiteCnt;
	}
	
	
	
    /**
     * checks whether the system of n-by-n grid percolate
     * 
     */	
	public boolean percolates() {
		for (int i = 1; i < N + 1; i++) {
			if (isFull(1, i)) return true;	
		}
		return false;
	}	
	
	public static void main(String[] args) { // test client (optional)
		StdOut.println("Testing cmd...");
		
		
	}	
}
