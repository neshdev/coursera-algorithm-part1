package prog1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private int experiments;
	private double[] percolationThreshold;

	private double mean;
	private double sigma;
	private double confidence;

	public PercolationStats(int N, int T) {

		if ( N <= 0){
			throw new java.lang.IllegalArgumentException("N has to be >= zero:" + N);
		}
		
		if ( T <= 0){
			throw new java.lang.IllegalArgumentException("T has to be >= zero:" + T);
		}
		
		this.experiments = T;
		this.percolationThreshold = new double[T];

		for (int i = 0; i < T; i++) {
			Percolation p = new Percolation(N);
			double count = 0;
			while (!p.percolates()) {
				int randomI = StdRandom.uniform(N)+1;
				int randomJ = StdRandom.uniform(N)+1;
				if (!p.isOpen(randomI, randomJ)) {
					p.open(randomI, randomJ);
					count++;
				}
			}		

			percolationThreshold[i] = count / (N * N);
		}

		double _mean = StdStats.mean(percolationThreshold);
		double _sigma = StdStats.stddev(percolationThreshold);

		this.mean = _mean;
		this.sigma = _sigma;
		this.confidence = 1.96 * Math.sqrt(_sigma)
				/ Math.sqrt(this.experiments);
	}

	public double mean() {
		return this.mean;
	}

	public double stddev() {
		return this.sigma;

	}

	public double confidenceLo() {
		return this.mean - confidence;
	}

	public double confidenceHi() {
		return this.mean + confidence;
	}

	public static void main(String[] args) {
		int N = StdIn.readInt();
		int T = StdIn.readInt();
		PercolationStats ps = new PercolationStats(N, T);
		StdOut.println("mean                    = " + ps.mean());
		StdOut.println("stddev                  = " + ps.stddev());
		StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
	}
}
