import java.util.Random;
public class Heapsort extends Heap
{
    public Heapsort(int[] arr)
    {
        super(arr.length);
        _capacity = arr.length;
        _size = arr.length;
        _elems = arr;
    }

    public void sort()
    {
        // adjust elements in the array to build the heap in-place
        int i = _size/2-1;
        while ( i >= 0 )
            movedown(i--,_size);

        // Now, sort the array
        int N = _size;
        while ( N > 0 )
        {
            swap(N-1,0);
            movedown(0,--N);
        }
    }

    public boolean isSorted()
    {
        for ( int i = 0; i < _size-1; i++ )
            if ( _elems[i+1] < _elems[i] )
                return false;
        return true;
    }

    public static void main(String[] args)
    {
        int capacity = 50;
        int[] arr = new int[capacity];
        Random rand = new Random();

        for ( int i = 0; i < capacity; i++ )
        {
            int nextInt = rand.nextInt(100);
            arr[i] = nextInt;
        }

        Heapsort hs = new Heapsort(arr);

        hs.displayme();
        hs.sort();
        hs.displayme();

        System.out.println("isSorted?:"+hs.isSorted());
    }
}
