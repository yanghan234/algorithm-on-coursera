import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;

public class Solver {
    private int numOfMoves = 0;
    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
        MinPQ<Node> pq = new MinPQ<Node>( new NodeComparator() );
        MinPQ<Node> pq2 = new MinPQ<Node>( new NodeComparator() );

        pq.insert( new Node( initial, null, 0 ) );
        pq2.insert( new Node( initial.twin(), null, 0 ) );

        Board next,next2;
        while (true)
        {
            next = nextStep(pq);
            next2 = nextStep(pq2);
            if ( next != null)// || next2 != null )
                break;
        }
        if ( next != null )
        {
            System.out.println("Printed by pq");
            System.out.println(next.toString());
        }
        if ( next2 != null )
        {
            System.out.println("Printed by pq2");
            System.out.println(next2.toString());
        }
    }

    //public boolean isSolvable()            // is the initial board solvable?
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
        return numOfMoves;
    }

    private Board nextStep( MinPQ<Node> pq )
    {
        if ( pq.isEmpty() ) return null;
        Node thisNode = pq.delMin();
        Board thisBoard = thisNode.thisBoard;
        //System.out.println(thisBoard.toString());
        if ( thisBoard.isGoal() ) return thisBoard;
        for ( Board b : thisBoard.neighbors() )
        {
            if ( thisNode.lastBoard == null || !b.equals( thisNode.lastBoard ) )
                pq.insert(new Node(b,thisBoard,thisNode.stepsToNow+1));
        }
        return null;
    }

    //public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    //{

    //}

    private class Node implements Comparable<Node> // Node in the game tree
    {
        public Board thisBoard;
        public Board lastBoard;
        public int stepsToNow;
        public int priority;
        public Node( Board _thisBoard, Board _lastBoard, int _stepsToNow )
        {
            thisBoard = _thisBoard;
            lastBoard = _lastBoard;
            stepsToNow = _stepsToNow;
            priority = stepsToNow + this.thisBoard.manhanttan();
        }

        public int compareTo( Node that )
        {
            if ( this.priority < that.priority )
                return -1;
            else if ( this.priority > that.priority )
                return 1;
            else
                return 0;
        }
    } // end of private class node

    private static class NodeComparator implements Comparator<Node>
    {
        public int compare( Node n1, Node n2 )
        {
            return n1.compareTo(n2);
        }
    }

    public static void main(String[] args)
    {
        int index = 0;
        while ( index++ < 30 )
        {
            System.out.println("index = "+index);
            String filename;
            filename = String.format("./test/puzzle4x4-%02d",index) + ".txt";
            In in = new In(filename);
            int n = in.readInt();
            int[][] arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = in.readInt();
                }
            }

            Board b = new Board( arr );

            Solver s = new Solver( b );
        }
    }
}
