import java.util.Random;
import java.lang.IllegalAccessError;
class Heap extends PQ
{
    public Heap( int capacity ) { super(capacity); };

    public void add( int item )
    {
        if ( isFull() )
            resize();
        _elems[_size] = item;
        _size++;
        moveup(_size-1);
    }

    public int get( )
    {
        if ( isEmpty() )
            throw new IllegalAccessError("Cannot access empty heap");
        swap(0,_size-1);
        int item = _elems[_size-1];
        _size--;
        movedown(0, _size);
        if ( _size < _capacity/4)
            shrink();
        return item;
    }

    protected void moveup( int index )
    {
        int me = index;
        int father = ( me - 1 ) / 2;
        while ( me > 0 && _elems[father] < _elems[me] )
        {
            swap(me,father);
            me = father;
            father = ( me - 1 ) / 2;
        }
    }

    protected void movedown( int index, int N )
    {
        int me = index;
        int leftKid = 2*me+1;
        int rightKid = 2*me+2;
        while ( me < N/2 )
        {
            if ( rightKid >= N )
            {
                if ( _elems[leftKid] > _elems[me] )
                    swap(leftKid,me);
                break;
            }
            if ( _elems[leftKid] <= _elems[me] && _elems[rightKid] <= _elems[me] )
                break;
            else
            {
                if ( _elems[leftKid] > _elems[rightKid] )
                {
                    swap(leftKid,me);
                    me = leftKid;
                }
                else
                {
                    swap(rightKid,me);
                    me = rightKid;
                }
            }
            leftKid = 2*me+1;
            rightKid = 2*me+2;
        }
    }

    public static void main(String[] args)
    {
        int capacity = 10;
        Heap pq = new Heap(capacity);
        Random rand = new Random();
        for ( int i = 0; i < 2*capacity; i++ )
        {
            int nextInt = rand.nextInt(2*i+1);
            pq.add(nextInt);
        }

        pq.displayme();

        for ( int i = 0; i < 10; i++ )
        {
            System.out.printf("%d,",pq.get());
        }
        System.out.printf("\n");

        pq.displayme();
    }
}

