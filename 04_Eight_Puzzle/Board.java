import java.util.Iterator;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdRandom;

public final class Board
{
    private int dim;
    private int[][] elems;
    private int manhattan;
    public Board( int[][] blocks )
    {
        elems = blocks;
        dim = blocks.length;
        manhattan = compManhattan();
    }

    public int dimension() { return dim; };

    public int hamming()
    {
        int numIncorrect = 0;
        for ( int i = 0; i < dim; i++ )
            for ( int j = 0; j < dim; j++ )
                if ( elems[i][j] != i*dim+j+1 && elems[i][j] != 0 )
                    numIncorrect++;
        return numIncorrect;
    }

    private int compManhattan()
    {
        int numSteps = 0;
        for ( int i = 0; i < dim; i++ )
        {
            for ( int j = 0; j < dim; j++ )
            {
                if ( elems[i][j] == i*dim+j+1 || elems[i][j] == 0 )
                    continue;
                else
                {
                    int icorrect = ( elems[i][j] - 1 ) / 3;
                    int jcorrect = ( elems[i][j] - 1 ) % 3;
                    numSteps += abs( i - icorrect ) + abs( j - jcorrect );
                }
            }
        }
        return numSteps;
    }

    public int manhattan()
    {
        return manhattan;
    }

    public boolean isGoal()
    {
        for ( int i = 0; i < dim; i++ )
            for ( int j = 0; j < dim; j++ )
                if ( elems[i][j] != ( i*dim+j+1 ) % (dim*dim) )
                    return false;
        return true;
    }

    public Board twin()
    {
        // randomly swap one pair, excluding blank block.
        int i = 0;
        int j = 1;
        if ( elems[i/dim][i%dim] == 0 )
            i = 2;
        else if ( elems[j/dim][j%dim] == 0 )
            j = 2;
        int[][] _elems = new int[dim][dim];
        for ( int ii = 0; ii < dim; ii++ )
            for ( int kk = 0; kk < dim; kk++ )
                _elems[ii][kk] = elems[ii][kk];
        Board b = new Board(_elems);
        b.swap(i,j);
        return b;
    }

    public boolean equals( Object y )
    {
        if ( y == null )
            return false;

        if ( y == this )
            return true;

        if ( y.getClass() != this.getClass() )
            return false;

        Board that = (Board) y;

        for ( int i = 0; i < dim; i++ )
            for ( int j = 0; j < dim; j++ )
                if ( that.elems[i][j] != this.elems[i][j] )
                    return false;
        return true;
    }

    public Iterable<Board> neighbors()
    {
        class BoardIterable implements Iterable<Board>
        {
            private Board[] neighborBoards;
            private int numOfNeighbors = 0;

            public BoardIterable()
            {
                int whichi = 0;
                int whichj = 0;
                for ( int i = 0; i < dim; i++ )
                {
                    for ( int j = 0; j < dim; j++ )
                    {
                        if ( elems[i][j] == 0 )
                        {
                            whichi = i;
                            whichj = j;
                            break;
                        }
                    }
                }

                if ( whichi+whichj == 0 || ( whichi == 0 && whichj == dim-1 )
                        || ( whichi == dim-1 && whichj == 0 )
                        || ( whichi == dim-1 && whichj == dim-1) )
                    numOfNeighbors = 2;
                else if ( whichi == 0 || whichi == dim-1 || whichj == 0 || whichj == dim-1 )
                    numOfNeighbors = 3;
                else
                    numOfNeighbors = 4;

                neighborBoards = new Board[numOfNeighbors];
                int index = 0;
                for ( int k = whichi-1; k <= whichi+1; k++ )
                {
                    for ( int l = whichj-1; l <= whichj+1; l++ )
                    {
                        if ( abs(k-whichi)+abs(l-whichj) == 1 && k >= 0 && l>= 0 && k < dim && l < dim  )
                        {
                            //System.out.println("whichi = "+whichi+", whichj = "+whichj);
                            //System.out.println("k = "+k+", l = "+l );
                            int[][] _elems = new int[dim][dim];
                            for ( int ii = 0; ii < dim; ii++ )
                                for ( int kk = 0; kk < dim; kk++ )
                                    _elems[ii][kk] = elems[ii][kk];
                            _elems[whichi][whichj] = _elems[k][l];
                            _elems[k][l] = 0;
                            neighborBoards[index] = new Board(_elems);
                            index++;
                        }
                    }
                }
            } // end of constructor

            public Iterator<Board> iterator()
            {
                return new Iterator<Board>()
                {
                    private int current = 0;
                    private int[] id = StdRandom.permutation(neighborBoards.length);
                    public boolean hasNext() { return current < numOfNeighbors; }
                    public Board next() {
                        Board item = neighborBoards[id[current]];
                        current++;
                        return item;
                    }
                };
            }
        }; // end of class iterable

        return new BoardIterable();
    } // end of the function neighbors()

    public String toString()
    {
        String s = Integer.toString(dim) + "\n";
        for ( int i = 0; i  < dim; i++ )
        {
            for ( int j = 0; j < dim; j++ )
            {
                s += Integer.toString(elems[i][j])+" ";
            }
            s += "\n";
        }
        return s;
    }

    private int abs( int x )
    {
        if ( x < 0 )
        {
            return -1*x;
        }
        else
        {
            return x;
        }
    }

    private void swap( int i, int j )
    {
        int tmp = elems[i/dim][i%dim];
        elems[i/dim][i%dim] = elems[j/dim][j%dim];
        elems[j/dim][j%dim] = tmp;
    }

    public static void main(String[] args)
    {
        int[][] arr = {{8,1,3},{4,0,2},{7,6,5}};

        Board b = new Board(arr);

        System.out.println("Dimension:"+b.dimension());
        System.out.println("Hamming:"+b.hamming());
        System.out.println("Manhattan:"+b.manhattan());
        System.out.println("isGoal:"+b.isGoal());
        System.out.println(b.toString());
        System.out.println("twin:\n"+b.twin().toString());

        for ( Board bb : b.neighbors() )
        {
            System.out.println(bb.manhattan());
            System.out.println(bb.toString());
        }

        System.out.println("equal to twin?:"+b.equals(b.twin()));

    }
}
