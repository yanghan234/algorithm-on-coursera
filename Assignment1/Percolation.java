import java.util.Random;
public class Percolation
{
    public int N;
    public int[] status; // 0: blocked, 1: open
    public int[] id; // id of the site or the sites that this site are connected to
    public int numOpen = 0;
    public Percolation(int num)                // create n-by-n grid, with all sites blocked
    {
        N = num;
        if ( N <= 0 )
            throw new IllegalArgumentException("N must be > 0");
        status = new int[N*N];
        id = new int[N*N+2];
        for ( int i = 0; i < N; i++ )
        {
            for ( int j = 0; j < N; j++ )
            {
                status[i*N+j] = 0;
                id[1+i*N+j] = 1+i*N+j;
            }
        }
        id[0] = 0;
        id[N*N+1] = N*N+1;
    }

    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        int index, index2;
        if ( !check_integrity(row,col) )
            throw new IllegalArgumentException("row and col must be within [1, N]!!");
        if ( isOpen(row, col) )
        {
            System.out.println("Block ( "+row+", "+col+" ) is already open.");
            return;
        }
        index = ( row - 1 ) * N + col - 1;
        status[index] = 1;
        numOpen++;
        // check up, down, left, right
        // if in the first row, set status to be filled and id to be connected to virtual point
        if ( row == 1 )
        {
            index = ( row - 1 ) * N + col - 1;
            id[index+1] = 0;
        }
        for ( int irow = row-1; irow <= row+1; irow++ )
        {
            for ( int icol = col-1; icol <= col+1; icol++ )
            {
                if ( (row-irow)*(row-irow)+(col-icol)*(col-icol)==1
                        && check_integrity(irow,icol)
                        && isOpen(irow,icol) )
                {
                    index  = (  row - 1 ) * N +  col - 1 + 1;
                    index2 = ( irow - 1 ) * N + icol - 1 + 1;
                    union(index,index2);
                }

            }
        }
    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        if ( !check_integrity(row,col) )
            throw new IllegalArgumentException("row and col must be within [1, N]!!");
        int index = ( row - 1 ) * N + col - 1;
        return status[index] == 1;
    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        if ( !check_integrity(row,col) )
            throw new IllegalArgumentException("row and col must be within [1, N]!!");
        int index = ( row - 1 ) * N + col - 1;
        int rootp = root(index+1);
        return rootp == 0;
    }

    public int numberOfOpenSites()       // number of open sites
    {
        return numOpen;
    }

    public boolean percolates()              // does the system percolate?
    {
        for ( int icol = 0; icol < N; icol++ )
        {
            int irow = N-1;
            int index = irow * N + icol;
            int rootp = root(index+1);
            if ( rootp == 0 )
                return true;
        }
        return false;
    }

    public void displayme( )
    {
        System.out.println("Status matrix:");
        for ( int row = 1; row <= N; row++ )
        {
            for ( int col = 1; col <= N; col++ )
            {
                int index = ( row - 1 ) * N + col - 1;
                System.out.printf("%d\t",status[index]);
            }
            System.out.printf("\n");
        }

        System.out.println("ID matrix:");
        for ( int row = 1; row <= N; row++ )
        {
            for ( int col = 1; col <= N; col++ )
            {
                int index = ( row - 1 ) * N + col - 1 + 1;
                System.out.printf("%d\t",id[index]);
            }
            System.out.printf("\n");
        }
    }

    public void write_to_file( )
    {

    }

    private boolean check_integrity( int row, int col )
    {
        if ( 1 <= row && row <= N && 1<= col && col <= N )
            return true;
        else
            return false;
    }

    private void union( int p, int q)
    {
        int rootp = root(p);
        int rootq = root(q);
        if ( rootp < rootq )
            id[rootq] = rootp;
        else
            id[rootp] = rootq;
    }

    private int root( int p )
    {
        int rootp = p;
        while ( rootp != id[rootp] )
            rootp = id[rootp];
        while ( p != rootp )
        {
            int newp = id[p];
            id[p] = rootp;
            p = newp;
        }
        return rootp;
    }

    public static void main(String[] args)   // test client (optional)
    {
        Percolation prc;
        int N = Integer.valueOf(args[0]);
        prc = new Percolation(N);
        Random rnd = new Random();
        while ( !prc.percolates() )
        {
            int irow = rnd.nextInt(N)+1;
            int icol = rnd.nextInt(N)+1;
            System.out.println("Open ( "+irow+", "+icol+" )");
            prc.open(irow,icol);
        }
        prc.displayme();
        System.out.println("Percolation? : " + prc.percolates());
        System.out.println("Fration of open block : " + prc.numberOfOpenSites()*1.0/(N*N) );
    }
}
