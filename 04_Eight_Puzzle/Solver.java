import java.util.Iterator;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    private int numOfMoves = 0;
    private Board[] path;
    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
        Board b = initial;
        MinPQ p = new MinPQ<Board>();

    }


    //public boolean isSolvable()            // is the initial board solvable?
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
        return numOfMoves;
    }

    //public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    //public static void main(String[] args) // solve a slider puzzle (given below)
    
    private class Node implements Comparable<Node>
    {
        // Nodes in the priority queue contain the information of a Board object
        // and some meta-data in the solving process.
        
        public int numOfSteps;
        public int priority;
        public Board board;
        public Node previous;
        Node( Board _board, Board  int whichstep )

    }
}
