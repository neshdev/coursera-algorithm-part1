import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

	private class SearchNode implements Comparable<SearchNode> {
		private Board board;
		private int moves;
		private SearchNode prev;
		private int priority;

		public SearchNode(Board b, int moves, SearchNode prev) {
			this.board = b;
			this.moves = moves;
			this.prev = prev;
			this.priority = b.manhattan();
		}

		public int compareTo(SearchNode that) {
			if (this.priority > that.priority)
				return 1;
			if (this.priority < that.priority)
				return -1;
			return 0;
		}
	}
	
	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial) {
		if (initial == null) {
			throw new NullPointerException();
		}

		MinPQ<SearchNode> pq = new MinPQ<Solver.SearchNode>();
		pq.insert(new SearchNode(initial, 0, null));

		
		while (true) {
			SearchNode minSn = pq.delMin();
			
			for (Board searchNode : minSn.board.neighbors()) {
				
				SearchNode sn = new SearchNode(searchNode, minSn.moves + 1 , minSn);
				if (!sn.equals(minSn.board)){
					pq.insert(sn);
				}
			}
			
			if ( pq.min().board.isGoal()){
				break;
			}
		}
		
		SearchNode solutionNode = pq.delMin();
		this.moves = solutionNode.moves;
		this.isSolvable = true;
		Stack<Board> boards = new Stack<Board>();
		SearchNode prev = solutionNode;
		while( prev != null){
			boards.push(prev.board);
			prev = solutionNode.prev;
		}
		
	}
	
	private boolean isSolvable = false;
	private int moves = -1;
	public Stack<Board> boards;

	// is the initial board solvable?
	public boolean isSolvable() {
		return isSolvable;
	}

	// min number of moves to solve initial board; -1 if unsolvable
	public int moves() {
		return moves;
	}

	// sequence of boards in a shortest solution; null if unsolvable
	public Iterable<Board> solution() {
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