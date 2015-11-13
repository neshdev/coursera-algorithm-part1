package prog4;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	
	private boolean solvable = false;
	private SearchNode last;

	private class SearchNode implements Comparable<SearchNode> {
		private Board board;
		private int moves;
		private SearchNode prev;
		private int priority;

		public SearchNode(Board b, int moves, SearchNode prev) {
			this.board = b;
			this.moves = moves;
			this.prev = prev;
			this.priority = b.manhattan() + moves;
		}

		public int compareTo(SearchNode that) {
			if (this.priority > that.priority)
				return 1;
			if (this.priority < that.priority)
				return -1;
			return 0;
		}
	}
	
	private void evalMinPriority(MinPQ<SearchNode> pq, boolean flipFlags){
		SearchNode minSn = pq.delMin();
		
		Board currentBoard = minSn.board;
		if (currentBoard.isGoal()){
			if (flipFlags){
				this.last = minSn;
			}
			this.solvable = true;
		}
		
		for (Board nextBoard : minSn.board.neighbors()) {
			if (minSn.prev == null || !nextBoard.equals(minSn.prev.board)){
				SearchNode sn = new SearchNode(nextBoard, minSn.moves + 1 , minSn);
				pq.insert(sn);
			}
		}
	}
	
	private void debugMinPriority(MinPQ<SearchNode> pq){
		SearchNode minSn = pq.delMin();
		
//		StdOut.println("manhattan:" + minSn.board.manhattan());
//		StdOut.println("moves:" + minSn.moves);
//		StdOut.println("priority:" + minSn.priority);
//		StdOut.println(minSn.board.toString());
		
		Board currentBoard = minSn.board;
		if ( currentBoard.isGoal()){
			this.last = minSn;
			this.solvable = true;
		}
		
		for (Board nextBoard : minSn.board.neighbors()) {
			if (minSn.prev == null || !nextBoard.equals(minSn.prev.board)){
				SearchNode sn = new SearchNode(nextBoard, minSn.moves + 1 , minSn);
//				StdOut.println("\tmanhattan:" + sn.board.manhattan());
//				StdOut.println("\tmoves:" + sn.moves);
//				StdOut.println("\tpriority:" + sn.priority);
//				String[] strings = sn.board.toString().split("\r\n");
//				for (int i = 0; i < strings.length; i++) {
//					StdOut.println("\t" + strings[i]);
//				}
				pq.insert(sn);
			}
		}
	}
	
	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial) {
		if (initial == null) {
			throw new NullPointerException();
		}

		MinPQ<SearchNode> pq = new MinPQ<Solver.SearchNode>();
		MinPQ<SearchNode> twinpq = new MinPQ<Solver.SearchNode>();
		
		pq.insert(new SearchNode(initial, 0, null));
		twinpq.insert(new SearchNode(initial.twin(), 0, null));

		while (!solvable) {
			evalMinPriority(pq, true);
			evalMinPriority(twinpq, false);			
		}
	}

	// is the initial board solvable?
	public boolean isSolvable() {
		return last != null;
	}

	// min number of moves to solve initial board; -1 if unsolvable
	public int moves() {
		return last == null ? -1 : last.moves;
	}

	// sequence of boards in a shortest solution; null if unsolvable
	public Iterable<Board> solution() {
		if (this.solvable == false) return null;
		
		Stack<Board> boards = new Stack<Board>();
		SearchNode prev = last;
		while( prev != null){
			boards.push(prev.board);
			prev = prev.prev;
		}
		return boards;
	}

	// solve a slider puzzle (given below)
	public static void main(String[] args) {
		In in = new In(args[0]);
		int N = in.readInt();
		int[][] blocks = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				blocks[i][j] = in.readInt();
		Board initial = new Board(blocks);

		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}
	}

}