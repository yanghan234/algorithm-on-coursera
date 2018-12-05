import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats
{
    private int N, nTrials;
    private double[] ratios;
    public PercolationStats(int n, int trials)
    {
        N = n;
        nTrials = trials;
        if ( N <= 0 || nTrials <= 0 )
            throw new IllegalArgumentException("N and nTrials must be >0!!");
        ratios = new double[nTrials];
        run();
    }

    public double mean()
    {
        return StdStats.mean(ratios);
    }

    public double stddev()
    {
        return StdStats.stddev(ratios);
    }

    public double confidenceLo()
    {
        return mean()-1.96*stddev()/Math.sqrt(nTrials);
    }

    public double confidenceHi()
    {
        return mean()+1.96*stddev()/Math.sqrt(nTrials);
    }

    private void run()
    {
        Percolation prc;
        for ( int itrial = 0; itrial < nTrials; itrial++ )
        {
            prc = new Percolation(N);
            while ( !prc.percolates() )
            {
                int irow = StdRandom.uniform(1,N+1);
                int icol = StdRandom.uniform(1,N+1);
                prc.open(irow,icol);
            }
            ratios[itrial] = prc.numberOfOpenSites()*1.0/(N*N);
            prc = null;
        }
    }

    public static void main(String[] args)
    {
        int n = new Integer(args[0]).intValue();
        int trials = new Integer(args[1]).intValue();
        System.out.println(n);
        System.out.println(trials);

        PercolationStats prcs = new PercolationStats(n,trials);

        System.out.println("mean    = "+prcs.mean());
        System.out.println("stddev  = "+prcs.stddev());
        System.out.println("95% confidence interval = ["+prcs.confidenceLo()+", "+prcs.confidenceHi()+"]");

    }


}
