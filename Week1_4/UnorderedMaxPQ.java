import java.util.Random;
public class UnorderedMaxPQ extends PQ
{
    public UnorderedMaxPQ( int capacity ) { super(capacity); };

    public void add( int item )
    {
        if ( isFull() )
            resize();
        _elems[_size++] = item;
    }

    public int get( )
    {
        int imax = 0;
        for ( int i = 0; i < _size; i++ )
            if ( _elems[imax] < _elems[i] )
                imax = i;
        swap(imax,_size-1);
        int item = _elems[_size-1];
        _size--;
        if ( _size < _capacity/4 )
            shrink();
        return item;
    }

    public static void main(String[] args)
    {
        int capacity = 100;
        UnorderedMaxPQ pq = new UnorderedMaxPQ(capacity);
        Random rand = new Random();
        for ( int i = 0; i < 2*capacity; i++ )
        {
            int nextInt = rand.nextInt(2*i+1);
            pq.add(nextInt);
            //System.out.println(pq.capacity());
        }
        for ( int i = 0; i < 151; i++ )
            System.out.printf("%d,",pq.get());
        System.out.printf("\n");
        pq.displayme();
    }
}
