import java.util.Random;
public abstract class Sort
{
    protected int N;
    protected int[] elems;

    public Sort ( int size )
    {
        N = size;
        elems = new int[N];
    }

    public void initialize( )
    {
        Random rand = new Random();

        for ( int i = 0; i < N; i++ )
        {
            int n = rand.nextInt(N) + 1;
            elems[i] = n;
        }
    }

    public void displayme( )
    {
        for ( int i = 0; i < N; i++ )
            System.out.printf("%d, ",elems[i]);
        System.out.printf("\n");
    }

    public boolean isSorted()
    {
        for ( int i = 0; i < N-1; i++ )
            if ( elems[i] > elems[i+1] )
                return false;
        return true;
    }

    public void sort() {};

    protected void swap( int i, int j )
    {
        int tmp = elems[i];
        elems[i] = elems[j];
        elems[j] = tmp;
    }
}
