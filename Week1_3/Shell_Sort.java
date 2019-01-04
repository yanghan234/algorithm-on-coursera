import java.time.Duration;
import java.time.Instant;

public class Shell_Sort extends Sort
{
    public Shell_Sort(int size)
    {
        super(size);
    }

    public void sort( )
    {
        int h = 1;
        while ( h < N / 3 ) h = h*3 + 1;  // h series: 1, 4, 13, 40, 121

        while ( h >= 1 )
        {
            for ( int i = h; i < N; i++ )
                for ( int j = i; j>=h && elems[j] < elems[j-h]; j = j - h )
                    swap( j, j-h );
            h = h / 3;
        }
    }

    public static void main(String[] args)
    {
        int nTest = 16;
        int N = 1;
        for ( int i = 1; i <= nTest; i++ )
        {
            N = N * 2;
            Sort ss = new Shell_Sort(N);
            ss.initialize();
            // ss.displayme();
            Instant start = Instant.now();
            ss.sort();
            Instant end = Instant.now();
            Duration dur = Duration.between(start,end);

            // ss.displayme();
            System.out.printf("N = %d, Runtime = %d ms, isSorted? = %b\n", N, dur.toMillis(), ss.isSorted());
        }
    }

}
