import java.util.Iterator;
import java.util.Comparator;
import java.util.Random;
import java.util.NoSuchElementException;
public class MinPQ<Key> implements Iterable<Key>
{
    private int capacity;
    private int size;
    private Key[] elems;
    Comparator<Key> cmptor;
    public MinPQ(int cap)
    {
        this.capacity = cap;
        elems = (Key[]) new Object[capacity];
        size = 0;
        cmptor = null;
    }

    public MinPQ()
    {
        this(1);
    }

    public MinPQ(Comparator<Key> cmptor)
    {
        this(1,cmptor);
    }

    public MinPQ(int cap, Comparator<Key> cmptor)
    {
        this(cap);
        this.cmptor = cmptor;
    }

    public void add(Key key)
    {
        elems[size] = key;
        size++;
        swim(size-1);
        if ( isFull() )
            this.resize(capacity*2);
    }

    public Key delMin()
    {
        swap(0,size-1);
        Key res = elems[--size];
        sink(0);
        if ( size <= capacity/4 )
            this.resize(capacity/2);
        return res;
    }

    public void delete(Key key)
    {
        int pos = -1;
        for ( int i = 0; i < size; i++ )
            if ( key.equals(elems[i]) )
            {
                pos = i;
                break;
            }
        if ( pos == -1 )
            return;
        swap(pos,size-1);
        size--;
        sink(pos);
        if ( size <= capacity/4 )
            this.resize(capacity/2);
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == capacity; }

    public String toString()
    {
        String res = "";
        for ( int i = 0; i < size; i++ )
        {
            res += elems[i].toString();
            res += ", ";
        }
        return res;
    }

    public Iterator<Key> iterator()
    {
        return new PQIterator();
    }

    private class PQIterator implements Iterator<Key>
    {
        private MinPQ<Key> copy;
        public PQIterator()
        {
            if ( cmptor == null )
                copy = new MinPQ<Key>(size);
            else
                copy = new MinPQ<Key>(size,cmptor);
            for ( int i = 0; i < size; i++ )
                copy.add(elems[i]);
        }
        public boolean hasNext() { return !copy.isEmpty(); }
        public Key next()
        {
            if ( !hasNext() )
                throw new NoSuchElementException();
            return copy.delMin();
        }
    }

    private boolean compare(int i, int j)
    {
        if ( cmptor == null )
            return ((Comparable<Key>) elems[i]).compareTo(elems[j]) > 0;
        else
            return cmptor.compare(elems[i],elems[j]) > 0;
    }

    private void resize(int newcapacity)
    {
        assert ( capacity == size ) || size <= capacity/4;
        Key[] newElems = (Key[]) new Object[newcapacity];
        for ( int i = 0; i < size; i++ )
            newElems[i] = elems[i];
        elems = newElems;
        capacity = newcapacity;
    }

    private void swim(int i)
    {
        int parent = (i-1)/2;
        while ( parent >= 0 )
        {
            if ( compare(parent,i) )
            {
                swap(parent,i);
                i = parent;
                parent = (i-1)/2;
            }
            else
                break;
        }
    }

    private void sink(int i)
    {
        while ( i < size/2 )
        {
            int kid = 2*i+1;
            if ( kid+1 < size && compare(kid,kid+1) ) kid++;
            if ( compare(i,kid) ) swap(i,kid);
            i = kid;
        }
    }

    private void swap(int i, int j)
    {
        Key key = elems[i];
        elems[i] = elems[j];
        elems[j] = key;
    }

    public static void main(String[] args)
    {
        Random rand = new Random();
        MinPQ<Integer> pq = new MinPQ<>();
        for ( int i = 0; i < 10; i++ )
            pq.add(rand.nextInt(100));
        for ( int i: pq )
            System.out.println(i);
    }
}
