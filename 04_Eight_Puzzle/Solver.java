import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Stack;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;

public class Solver {
    private boolean solvable;
    private Node finalNode, finalNode2;
    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
        MinPQ<Node> pq = new MinPQ<Node>( new NodeComparator() );
        MinPQ<Node> pq2 = new MinPQ<Node>( new NodeComparator() );

        pq.insert( new Node( initial, null, 0 ) );
        pq2.insert( new Node( initial.twin(), null, 0 ) );

        while (true)
        {
            finalNode = nextStep(pq);
            finalNode2 = nextStep(pq2);
            if ( finalNode != null || finalNode2 != null )
                break;
        }
        if ( finalNode != null )
        {
            solvable = true;
            //System.out.println("Printed by pq");
            //System.out.println(finalNode.thisBoard.toString());
        }
        if ( finalNode2 != null )
        {
            solvable = false;
            //System.out.println("Printed by pq2");
            //System.out.println(finalNode2.thisBoard.toString());
        }
    }

    public boolean isSolvable()            // is the initial board solvable?
    {
        return solvable;
    }

    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
        if ( solvable )
            return finalNode.stepsToNow;
        else
            return -1;
    }

    private Node nextStep( MinPQ<Node> pq )
    {
        if ( pq.isEmpty() ) return null;
        Node thisNode = pq.delMin();
        Board thisBoard = thisNode.thisBoard;
        //System.out.println(thisBoard.toString());
        if ( thisBoard.isGoal() ) return thisNode;
        for ( Board b : thisBoard.neighbors() )
        {
            if ( thisNode.lastNode == null || !b.equals( thisNode.lastNode.thisBoard ) )
                pq.insert(new Node(b,thisNode,thisNode.stepsToNow+1));
        }
        return null;
    }

    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
        if ( !solvable )
            return null;

        Stack<Board> s = new Stack<Board>();

        Node thisNode = finalNode;
        while ( thisNode != null )
        {
            s.push( thisNode.thisBoard );
            thisNode = thisNode.lastNode;
        }

        ArrayList<Board> arr = new ArrayList<Board>();
        while( !s.empty() )
        {
            arr.add( s.pop() );
        }

        return arr;
    }

    private class Node implements Comparable<Node> // Node in the game tree
    {
        public Board thisBoard;
        public Node lastNode;
        public int stepsToNow;
        public int priority;
        public Node( Board _thisBoard, Node  _lastNode, int _stepsToNow )
        {
            thisBoard = _thisBoard;
            lastNode = _lastNode;
            stepsToNow = _stepsToNow;
            priority = stepsToNow + this.thisBoard.manhattan();
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
        while ( index++ < 2 )
        {
            System.out.println("index = "+index);
            String filename;
            filename = String.format("./test/puzzle3x3-%02d",index) + ".txt";
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

            System.out.println("isSolvable?: "+s.isSolvable());
            System.out.println("moves: "+s.moves());
            for ( Board bb: s.solution() )
                System.out.println(bb.toString());

        }
    }
}
