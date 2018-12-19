public final class Board
{
    private int dim;
    private int[][] elems;
    private int manPri;
    public Board( int[][] blocks )
    {
        elems = blocks;
        dim = blocks.length;
        manPri = compManhattan();
    };

    public int dimension() { return dim; };

    public int hamming()
    {
        int numIncorrect = 0;
        for ( int i = 0; i < dim; i++ )
            for ( int j = 0; j < dim; j++ )
                if ( elems[i][j] != i*dim+j+1 && elems[i][j] != 0 )
                    numIncorrect++;
        return numIncorrect;
    };

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
        return manPri;
    }

    public boolean isGoal()
    {
        for ( int i = 0; i < dim; i++ )
            for ( int j = 0; j < dim; j++ )
                if ( elems[i][j] != i*dim+j+1 )
                    return false;
        return true;
    };

    public Board twin()
    {
        // randomly swap one pair, excluding blank block.
        int i = 0;
        int j = 1;
        if ( elems[i/dim][i%dim] == 0 )
            i = 2;
        else if ( elems[j/dim][j%dim] == 0 )
            j = 2;
        Board b = new Board(elems);
        b.swap(i,j);
        return b;
    }
    //public boolean equals( Object y );
    public Iterable<Board> neighbors()
    {
        return new Iterable<Board>()
        {
            public Iterator<Board> iterator()
            {
                private current;
                public Iterator( Board[] 
                public boolean hasNext()
                {

                }

                public 

            }
        }
    }

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
            return -1*x;
        else
            return x;
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

    }
}
