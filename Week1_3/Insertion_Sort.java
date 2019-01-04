import java.time.Instant;
import java.time.Duration;
public class Insertion_Sort extends Sort
{
    public Insertion_Sort ( int size )
    {
        super(size);
    }

    public void sort( )
    {
        for ( int i = 1; i < N; i++ )
        {
            int val = elems[i];
            int j = i - 1;
            for ( ; j >= 0; j-- )
            {
                if ( elems[j] > val )
                    elems[j+1] = elems[j];
                else
                    break;
            }
            elems[j+1] = val;
        }
    }

    public static void main(String[] args)
    {
        int nTest = 16;
        int N = 1;
        for ( int i = 1; i <= nTest; i++ )
        {
            N = N * 2;
            Sort ss = new Insertion_Sort(N);
            ss.initialize();

            Instant start = Instant.now();
            // ss.displayme();
            ss.sort();
            // ss.displayme();
            Instant end = Instant.now();
            Duration dur = Duration.between(start,end);

            System.out.printf("N = %d, Runtime = %d ms, isSorted? = %b\n", N, dur.toMillis(), ss.isSorted());

        }
    }
}
