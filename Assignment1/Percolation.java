import edu.princeton.cs.algs4.StdRandom;
public class Percolation
{
    private int N;
    private int[] status; // 0: blocked, 1: open
    private int[] id; // id of the site or the sites that this site are connected to
    private int numOpen = 0;
    public Percolation(int num)                // create n-by-n grid, with all sites blocked
    {
        N = num;
        if (N <= 0)
            throw new IllegalArgumentException("N must be > 0");
        status = new int[N*N];
        id = new int[N*N+2];
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
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
        if (!check_integrity(row,col))
            throw new IllegalArgumentException("row and col must be within [1, N]!!");
        if (isOpen(row, col))
        {
            //System.out.println("Block ("+row+", "+col+") is already open.");
            return;
        }
        index = (row - 1) * N + col - 1;
        status[index] = 1;
        numOpen++;
        // check up, down, left, right
        // if in the first row, set status to be filled and id to be connected to virtual point
        if (row == 1)
        {
            index = (row - 1) * N + col - 1;
            id[index+1] = 0;
        }
        for (int irow = row-1; irow <= row+1; irow++)
        {
            for (int icol = col-1; icol <= col+1; icol++)
            {
                if ((row-irow)*(row-irow)+(col-icol)*(col-icol)==1
                        && check_integrity(irow,icol)
                        && isOpen(irow,icol))
                {
                    index  = ( row - 1) * N +  col - 1 + 1;
                    index2 = (irow - 1) * N + icol - 1 + 1;
                    union(index,index2);
                }

            }
        }
    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        if (!check_integrity(row,col))
            throw new IllegalArgumentException("row and col must be within [1, N]!!");
        int index = (row - 1) * N + col - 1;
        return status[index] == 1;
    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        if (!check_integrity(row,col))
            throw new IllegalArgumentException("row and col must be within [1, N]!!");
        int index = (row - 1) * N + col - 1;
        int rootp = root(index+1);
        return rootp == 0;
    }

    public int numberOfOpenSites()       // number of open sites
    {
        return numOpen;
    }

    public boolean percolates()              // does the system percolate?
    {
        for (int icol = 0; icol < N; icol++)
        {
            int irow = N-1;
            int index = irow * N + icol;
            int rootp = root(index+1);
            if (rootp == 0)
                return true;
        }
        return false;
    }

//    public void displayme()
//    {
//        System.out.println("Status matrix:");
//        for (int row = 1; row <= N; row++)
//        {
//            for (int col = 1; col <= N; col++)
//            {
//                int index = (row - 1) * N + col - 1;
//                System.out.printf("%d\t",status[index]);
//            }
//            System.out.printf("\n");
//        }
//
//        System.out.println("ID matrix:");
//        for (int row = 1; row <= N; row++)
//        {
//            for (int col = 1; col <= N; col++)
//            {
//                int index = (row - 1) * N + col - 1 + 1;
//                System.out.printf("%d\t",id[index]);
//            }
//            System.out.printf("\n");
//        }
//    }

    //public void write_to_file(int iter)
    //{
    //    try
    //    {
    //        FileWriter fw = new FileWriter("./snapshots",true);
    //        BufferedWriter bw = new BufferedWriter(fw);
    //        if (iter == 1)
    //            bw.write("iter,row,col,status,id\n");
    //        for (int i = 0; i<N; i++)
    //        {
    //            for (int j = 0; j<N; j++)
    //            {
    //                int index = i*N+j;
    //                String str = String.format("%4d,%4d,%4d,%4d,%4d\n",iter,i+1,j+1,status[index],root(index+1));
    //                bw.write(str);
    //            }
    //        }
    //        bw.write("\n");
    //        bw.close();
    //    }
    //    catch (IOException e) {
    //        System.out.println("Snapshot IO Exception");
    //    }
    //    finally
    //    {    return; }
    //}

    private boolean check_integrity(int row, int col)
    {
        if (1 <= row && row <= N && 1<= col && col <= N)
            return true;
        else
            return false;
    }

    private void union(int p, int q)
    {
        int rootp = root(p);
        int rootq = root(q);
        if (rootp < rootq)
            id[rootq] = rootp;
        else
            id[rootp] = rootq;
    }

    private int root(int p)
    {
        int rootp = p;
        while (rootp != id[rootp])
            rootp = id[rootp];
        while (p != rootp)
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
        while (!prc.percolates())
        {
            int irow = StdRandom.uniform(1,N+1);
            int icol = StdRandom.uniform(1,N+1);
            System.out.println("Open ("+irow+", "+icol+")");
            prc.open(irow,icol);
        }
        System.out.println("Percolation? : " + prc.percolates());
        System.out.println("Fration of open block : " + prc.numberOfOpenSites()*1.0/(N*N));
    }
}
