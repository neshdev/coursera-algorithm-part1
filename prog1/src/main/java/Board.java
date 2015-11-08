import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Board {

	private static int EMPTY_SLOT = 0;

	private int[][] tiles;
	private int[][] goals;

	// construct a board from an N-by-N array of blocks
	// (where blocks[i][j] = block in row i, column j)
	public Board(int[][] blocks) {
		if (blocks == null) {
			throw new NullPointerException("blocks cannot be null");
		}

		this.goals = new int[blocks.length][];
		this.tiles = new int[blocks.length][];
		int goal = 1;

		for (int i = 0; i < blocks.length; i++) {
			goals[i] = new int[blocks[i].length];
			tiles[i] = new int[blocks[i].length];
			for (int j = 0; j < blocks[i].length; j++) {
				goals[i][j] = goal;
				tiles[i][j] = blocks[i][j];
				goal++;
			}
		}
		this.tiles = blocks;
		goals[blocks.length - 1][blocks.length - 1] = EMPTY_SLOT;

	}

	// board dimension N
	public int dimension() {
		return tiles.length;
	}

	// number of blocks out of place
	public int hamming() {
		int dim = this.dimension();
		int hammingCount = 0;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {

				if (i == dim - 1 && j == dim - 1)
					continue;

				int block = tiles[i][j];
				int goal = goals[i][j];
				if (block != goal) {
					hammingCount++;
				}
			}
		}
		return hammingCount;
	}

	// sum of Manhattan distances between blocks and goal
	public int manhattan() {
		int dim = this.dimension();
		int manhattanCount = 0;
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (this.tiles[i][j] == EMPTY_SLOT)
					continue;
				
				int manhattanDistance = distanceFromGoal(i,j);				
				manhattanCount = manhattanCount + manhattanDistance;
			}
		}
		return manhattanCount;
	}
	
	private int distanceFromGoal(int i, int j){
		int goal = this.tiles[i][j];
		int x, y;
		
		int rem = goal % this.dimension();
		if (rem == 0){
			y = this.dimension() - 1;
			x = goal / this.dimension() - 1;
		} else {
			y = rem - 1;
			x = goal / this.dimension();
		}
		return Math.abs(x - i) + Math.abs(y - j);
	}

	// is this board the goal board?
	public boolean isGoal() {
		boolean isGoal = true;
		for (int i = 0; i < this.dimension(); i++) {
			for (int j = 0; j < this.dimension(); j++) {
				if (this.tiles[i][j] != goals[i][j]) {
					isGoal = false;
					break;
				}
			}
		}
		return isGoal;
	}

	// a board that is obtained by exchanging any pair of blocks
	public Board twin() {
		int[][] aTwin = deepCopy(tiles);
        if (aTwin[0][0] * aTwin[0][1] == 0) {
            exchTiles(aTwin, 1, 0, 1, 1);
        } else {
        	exchTiles(aTwin, 0, 0, 0, 1);
        }
        return new Board(aTwin);
	}

	// does this board equal y?
	public boolean equals(Object y) {
		if (y == this)
			return true;

		if (y == null)
			return false;

		if (y.getClass() != this.getClass())
			return false;

		Board that = (Board) y;
		return Arrays.deepEquals(that.tiles, this.tiles);

	}

	private void addleftSwapBoard(ArrayList<Board> boards, int i, int j) {
		if (i == 0) return;
		int[][] copyTiles = deepCopy(this.tiles);
		exchTiles(copyTiles, i, j, i - 1, j);
		Board b = new Board(copyTiles);
		boards.add(b);
	}

	private void addrightSwapBoard(ArrayList<Board> boards, int i, int j) {
		if (i == this.dimension() - 1) return;
		int[][] copyTiles = deepCopy(this.tiles);
		exchTiles(copyTiles, i, j, i + 1, j);
		Board b = new Board(copyTiles);
		boards.add(b);
	}

	private void addtopSwapBoard(ArrayList<Board> boards, int i, int j) {
		if ( j == 0) return;
		int[][] copyTiles = deepCopy(this.tiles);
		exchTiles(copyTiles, i, j, i, j - 1);
		Board b = new Board(copyTiles);
		boards.add(b);
	}

	private void addbottomSwapBoard(ArrayList<Board> boards, int i, int j) {
		if ( j == this.dimension() - 1) return;
		int[][] copyTiles = deepCopy(this.tiles);
		exchTiles(copyTiles, i, j, i, j + 1);
		Board b = new Board(copyTiles);
		boards.add(b);
	}

	private int[][] deepCopy(int[][] arr) {
		int size = this.dimension();
		int[][] copy = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				copy[i][j] = arr[i][j];
			}
		}
		return copy;
	}

	private void exchTiles(int[][] arr, int x, int y, int v, int w) {
		int swap = arr[x][y];
		arr[x][y] = arr[v][w];
		arr[v][w] = swap;
	}

	// all neighboring boards
	public Iterable<Board> neighbors() {
		ArrayList<Board> boards = new ArrayList<Board>();
		ol: for (int i = 0; i < this.dimension(); i++) {
			for (int j = 0; j < this.dimension(); j++) {
				int tile = tiles[i][j];
				if (tile == EMPTY_SLOT) {
					addleftSwapBoard(boards, i, j);
					addrightSwapBoard(boards, i, j);
					addtopSwapBoard(boards, i, j);
					addbottomSwapBoard(boards, i, j);
					break ol;
				}
			}

		}
		return boards;
	}

	// string representation of this board (in the output format specified
	// below)
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.dimension() + "\r\n");
		for (int i = 0; i < this.dimension(); i++) {
			for (int j = 0; j < this.dimension(); j++) {
				int position = tiles[i][j];
				if (j == this.dimension() - 1) {
					sb.append(" " + position + "\r\n");
				} else {
					sb.append(" " + position + " ");
				}
			}
		}
		return sb.toString();
	}

	// unit tests (not graded)
	public static void main(String[] args) {
	}
}