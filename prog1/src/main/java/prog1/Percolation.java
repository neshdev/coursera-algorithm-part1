package prog1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private boolean[][] openSites;
	private int topSiteIndex;
	private int bottomSiteIndex;

	private WeightedQuickUnionUF uf;
	private int n;

	public Percolation(int N) {

		if (N <= 0) {
			throw new java.lang.IllegalArgumentException (
					"Invalid N-by-N grid :  " + N);
		}

		this.n = N;
		final int totalNodes = N * N + 2;
		this.topSiteIndex = 0;
		this.bottomSiteIndex = N * N + 1;
		this.uf = new WeightedQuickUnionUF(totalNodes);
		this.openSites = new boolean[N][N];

		for (int i = 1; i <= N; i++) {
			int openIndex1 = convert2DInto1D(i, 1);
			uf.union(topSiteIndex, openIndex1);
			
			int openIndex2 = convert2DInto1D(i, n);
			uf.union(bottomSiteIndex, openIndex2);
		}
	}

	private void validate(int i, int j) {
		if (i < 1 || i > this.n) {
			throw new java.lang.IndexOutOfBoundsException(
					"Index i out of range :" + i);
		}

		if (j < 1 || j > this.n) {
			throw new java.lang.IndexOutOfBoundsException(
					"Index j out of range :" + j);
		}
	}

	public void open(int i, int j) {
		validate(i, j);

		if (isOpen(i, j)) {
			return;
		}

		openSites[i - 1][j - 1] = true;

		connectLeft(i, j);
		connectRight(i, j);
		connectTop(i, j);
		connectBottom(i, j);
	}

	private int convert2DInto1D(int i, int j) {
		int index = (i - 1) * n + j;
		return index;
	}

	private void connectLeft(int i, int j) {
		if (i == 1 || !isOpen(i - 1, j)) {
			return;
		}

		int openIndex = convert2DInto1D(i, j);
		int targetIndex = convert2DInto1D(i - 1, j);
		uf.union(openIndex, targetIndex);
	}

	private void connectRight(int i, int j) {
		if (i == n || !isOpen(i + 1, j)) {
			return;
		}

		int openIndex = convert2DInto1D(i, j);
		int targetIndex = convert2DInto1D(i + 1, j);
		uf.union(openIndex, targetIndex);
	}

	private void connectTop(int i, int j) {
		if (j == 1 || !isOpen(i, j - 1)) {
			return;
		}

		int openIndex = convert2DInto1D(i, j);
		int targetIndex = convert2DInto1D(i, j - 1);
		uf.union(openIndex, targetIndex);
	}

	private void connectBottom(int i, int j) {
		if (j == n || !isOpen(i, j + 1)) {
			return;
		}

		int openIndex = convert2DInto1D(i, j);
		int targetIndex = convert2DInto1D(i, j + 1);
		uf.union(openIndex, targetIndex);
	}

	public boolean isOpen(int i, int j) {
		validate(i, j);
		return openSites[i - 1][j - 1];
	}

	public boolean isFull(int i, int j) {
		validate(i, j);
		int siteIndex = convert2DInto1D(i, j);
		return uf.connected(topSiteIndex, siteIndex);
	}

	public boolean percolates() {
		return uf.connected(topSiteIndex, bottomSiteIndex);
	}

	public static void main(String[] args) {

	}
}