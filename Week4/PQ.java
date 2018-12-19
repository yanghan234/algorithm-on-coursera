import java.lang.IllegalArgumentException;
public abstract class PQ
{
    protected int _capacity;
    protected int _size;
    protected int[] _elems;
    public PQ( )
    {
        _capacity = 2;
        _size = 0;
        _elems = new int[_capacity];
    };
    public PQ( int capacity )
    {
        if ( capacity <= 0 )
            throw new IllegalArgumentException("Capacity must be positive integer");
        _capacity = capacity;
        _size = 0;
        _elems = new int[_capacity];
    }

    public int size() { return _size; };
    public int capacity() { return _capacity; };
    public int elems(int index) { return _elems[index]; };
    public boolean isEmpty() { return _size == 0; };
    public boolean isFull() { return _size == _capacity; };

    public abstract void add( int item );
    public abstract int get();

    protected void swap( int i, int j )
    {
        int tmp = _elems[i];
        _elems[i] = _elems[j];
        _elems[j] = tmp;
    }

    public void displayme()
    {
        for ( int i = 0; i < _size; i++ )
            System.out.printf("%d,",_elems[i]);
        System.out.print("\n");
    }

    public void resize( int newCapacity )
    {
        assert isFull();
        assert newCapacity > _capacity;
        int[] tmp = new int[newCapacity];
        for ( int i = 0; i < _size; i++ )
            tmp[i] = _elems[i];
        _elems = tmp;
        _capacity = newCapacity;
    }

    public void resize()
    {
        resize(2*_capacity);
    }

    public void shrink()
    {
        resize(_capacity/2);
    }

}
