import java.time.Duration;
import java.time.Instant;

public class Selection_Sort extends Sort
{
    public Selection_Sort( int size )
    {
        super(size);
    }

    public void sort( )
    {
        for ( int i = 0; i < N-1; i++ )
        {
            int minInd = i;
            for ( int j = i+1; j < N; j++ )
                if ( elems[j] < elems[minInd] )
                    minInd = j;
            swap(i,minInd);
        }
    }

    public static void main(String[] args)
    {
        int nTest = 16;
        int N = 1;
        for ( int i = 1; i <= nTest; i++ )
        {
            N = N * 2;
            Sort ss = new Selection_Sort(N);
            ss.initialize();

            Instant start = Instant.now();
            ss.sort();
            Instant end = Instant.now();
            Duration dur = Duration.between(start,end);

            System.out.printf("N = %d, Runtime = %d ms, isSorted? = %b\n", N, dur.toMillis(), ss.isSorted());

        }
    }
}
