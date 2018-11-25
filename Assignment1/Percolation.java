//import java.lang.IllegalArgumentException;
public class Percolation
{
    private int N;
    private int[][] _status;
    private int[][] _id;
    private int numOpen = 0;
    public Percolation(int num)                // create n-by-n grid, with all sites blocked
    {
        N = num;
        if ( N <= 0 )
            throw new IllegalArgumentException("N must be > 0");
        _status = new int[N][N];
        _id = new int[N][N];
        for ( int i = 0; i < N; i++ )
        {
            for ( int j = 0; j < N; j++ )
            {
                _status[i][j] = 0;
                _id[i][j] = i*N+j;
            }
        }
    }
    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        if ( !isOpen(row, col) )
        {
            _status[row][col] = 1;
            numOpen++;
        }
    }
    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        return _status[row][col] == 1;
    }

    //public boolean isFull(int row, int col)  // is site (row, col) full?
    public int numberOfOpenSites()       // number of open sites
    {
        return numOpen;
    }
    //public boolean percolates()              // does the system percolate?

    public static void main(String[] args)   // test client (optional)
    {
        Percolation prc;
        prc = new Percolation(4);
        System.out.println(prc.numberOfOpenSites());

    }
}
