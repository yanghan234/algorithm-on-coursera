import java.util.Random;
public class OrderedMaxPQ extends PQ
{
    public OrderedMaxPQ() { super(); };
    public OrderedMaxPQ( int capacity ) { super(capacity); };

    public void add( int item )
    {
        if ( isFull() )
            resize();
        _size++;
        int i = _size-2;
        while ( i >= 0 && _elems[i] > item )
        {
            _elems[i+1] = _elems[i];
            i--;
        }
        _elems[i+1] = item;
    }

    public int get( )
    {
        int item = _elems[_size-1];
        _size--;
        if ( _size < _capacity/4 )
            shrink();
        return item;
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
        int capacity = 100;
        OrderedMaxPQ pq = new OrderedMaxPQ(capacity);
        Random rand = new Random();
        for ( int i = 0; i < 2*capacity; i++ )
        {
            int nextInt = rand.nextInt(2*i+1);
            pq.add(nextInt);
            //System.out.println(pq.capacity());
        }
        System.out.println("isSorted?:"+pq.isSorted());
        for ( int i = 0; i < 151; i++ )
            pq.get();
        //System.out.println(pq.get());
        pq.displayme();
    }
}

