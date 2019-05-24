import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	
	private Percolation pr;
	private double[] fractions;
	private int experimentCnt;

	/***
	 * 
	 * @param initiates n-n grid
	 * @param trials
	 * Perform trials independent experiments on an n-by-n grid
	 */
	
	public PercolationStats(int n, int trials) {
		if ((n <= 0) || (trials <= 0)) throw new IllegalArgumentException("Grid or trials cannot be negatively initiated");
		experimentCnt = trials;
		
		fractions = new double[trials];
		
		for (int expNo = 0; expNo < trials; expNo++) {
			
			pr = new Percolation(n);
			
			int openSites = 0;
			while(!pr.percolates()){
                int i = StdRandom.uniform(1, n + 1);
                int j = StdRandom.uniform(1, n + 1);
                if (!pr.isOpen(i, j)) pr.open(i, j);
                openSites++;
			}
			
			double fraction = (double) openSites/(n*n);
			
			fractions[expNo] = fraction;
		}	
	}
	
	
	/***
	 * 
	 * @return Sample mean of percolation threshold
	 */
	
	public double mean() {
		
		return StdStats.mean(fractions);
	}
	
	/***
	 * 
	 * @returnSample standard deviation of percolation threshold
	 */
  	public double stddev() {
  		
  		return StdStats.stddev(fractions);
  	}
  	
  	/***
  	 * @return Low  endpoint of 95% confidence interval
  	 */
  	
  	public double confidenceLo() {
  		return mean() - ((1.96 * stddev()) / Math.sqrt(experimentCnt));
  	}
  	
  	/***
  	 * 
  	 * 
  	 * 
  	 * @return High endpoint of 95% confidence interval	
  	 */
  	public double confidenceHi() {
  		return mean() + ((1.96 * stddev()) / Math.sqrt(experimentCnt));
  	}
	
	public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(N, T);

        String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidence);

	}

}
