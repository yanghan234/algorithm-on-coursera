import java.time.Instant;
import java.time.Duration;

public class Quick_Sort extends Sort
{

    public Quick_Sort( int size )
    {
        super(size);
    }

    public void sort( )
    {
       // elems[0] = 5;
       // elems[1] = 2;
       // elems[2] = 1;
       // elems[3] = 3;
       // elems[4] = 2;
       // displayme();
        int lo = 0;
        int hi = N - 1;
        sort_kernel(lo,hi);
    }

    public void sort_kernel(int lo, int hi)
    {
        if ( lo == hi )
            return;
        else if ( lo + 1 == hi && elems[lo] > elems[hi] )
        {
            swap(lo,hi);
            return;
        }
        else if ( lo + 1 == hi && elems[lo] <= elems[hi] )
            return;

        int pi = lo; // choose the left end element as pivot
        int i = lo+1;
        int j = hi;

        while ( true )
        {
            while ( i <= hi && elems[i] <= elems[pi] )
                i++;
            while ( j >= lo + 1 && elems[j] > elems[pi] )
                j--;
            if ( j <= i )
                break;
            swap(i,j);
        }
        swap(j,pi);
        pi = j;

        if ( pi == hi )
            sort_kernel(lo,pi-1);
        else if ( pi == lo )
            sort_kernel( pi+1, hi);
        else
        {
            sort_kernel(lo,pi-1);
            sort_kernel(pi+1,hi);
        }
    }

    public static void main(String[] args)
    {
        int nTest = 20;
        int N = 1;
        for ( int i = 1; i <= nTest; i++ )
        {
            N = N * 2;
            Sort qs = new Quick_Sort(N);
            qs.initialize();

            Instant start = Instant.now();
            qs.sort();
            Instant end = Instant.now();
            Duration dur = Duration.between(start,end);

            System.out.printf("N = %d, Runtime = %d ms, isSorted? = %b\n", N, dur.toMillis(), qs.isSorted());
        }
    }

}
