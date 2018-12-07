import java.time.Instant;
import java.time.Duration;
public class Merge_Sort extends Sort
{
    public Merge_Sort(int size)
    {
        super(size);
    }

    public void sort( )
    {
        int begin = 0;
        int end = N - 1;
        sort_kernel( elems, begin, end );
    }

    private void sort_kernel(int[] arr, int begin, int end )
    {
        if ( begin == end )
            return;
        else if ( begin + 1 == end )
        {
            if ( elems[begin] > elems[end] )
                swap(begin,end);
        }
        else
        {
            int mid = ( begin + end ) / 2;
            sort_kernel( arr, begin, mid );
            sort_kernel( arr, mid+1, end );
            merge(arr, begin, mid, end );
        }

    }

    private void merge( int[] arr, int begin, int mid, int end )
    {
        int[] aux = new int[end-begin+1];
        int i = begin;
        int j = mid+1;
        int k = 0;
        while ( i <= mid || j <= end )
        {
            if ( i > mid ) aux[k++] = arr[j++];
            else if ( j > end ) aux[k++] = arr[i++];
            else if ( arr[i] <= arr[j] ) aux[k++] = arr[i++];
            else aux[k++] = arr[j++];
        }
        k = 0;
        while ( k < (end-begin)+1 )
        {
            arr[begin+k] = aux[k];
            k++;
        }
    }

    public static void main(String[] args)
    {
        int nTest = 16;
        int N = 1;
        for ( int i = 1; i <= nTest; i++ )
        {
            N = N * 2;
            Sort ss = new Merge_Sort(N);
            ss.initialize();

            Instant start = Instant.now();
            ss.sort();
            Instant end = Instant.now();
            Duration dur = Duration.between(start,end);

            System.out.printf("N = %d, Runtime = %d ms, isSorted? = %b\n", N, dur.toMillis(), ss.isSorted());
        }
    }

}
