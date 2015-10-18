package prog1;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.princeton.cs.algs4.StdOut;

public class PercolationStatsTest {

	@Test
	public void Test_200by100Test() {
		int N = 200;
		int T = 100;
		PercolationStats ps = new PercolationStats(N, T);
		StdOut.println(N + " by " + T + " Test");
		StdOut.println("mean                    = " + ps.mean());
		StdOut.println("stddev                  = " + ps.stddev());
		StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
	}

	@Test
	public void Test_200by100Test_Take2() {
		int N = 200;
		int T = 100;
		PercolationStats ps = new PercolationStats(N, T);
		StdOut.println(N + " by " + T + " Test");
		StdOut.println("mean                    = " + ps.mean());
		StdOut.println("stddev                  = " + ps.stddev());
		StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
	}

	@Test
	public void Test_2by10000Test() {
		int N = 2;
		int T = 10000;
		PercolationStats ps = new PercolationStats(N, T);
		StdOut.println(N + " by " + T + " Test");
		StdOut.println("mean                    = " + ps.mean());
		StdOut.println("stddev                  = " + ps.stddev());
		StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
	}

	@Test
	public void Test_2by100000Test() {
		int N = 2;
		int T = 100000;
		StdOut.println(N + " by " + T + " Test");
		PercolationStats ps = new PercolationStats(N, T);
		StdOut.println("mean                    = " + ps.mean());
		StdOut.println("stddev                  = " + ps.stddev());
		StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
	}

}
