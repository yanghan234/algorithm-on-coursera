import java.util.Iterator;
import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item>
{
    private Item[] elems;
    private int count;
    private int head, tail;

    // constructor
    public RandomizedQueue()
    {
        elems = (Item[]) new Object[2];
        count = 0;
        head = tail = 0;
    }

    public boolean isEmpty() { return count == 0; };

    public int size() { return count; };

    private void resize(int capacity)
    {
        assert capacity >= count;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < count; i++)
        {
            temp[i] = elems[(head + i) % elems.length];
        }
        elems = temp;
        head = 0;
        tail = count;
    }

    public void enqueue(Item item)
    {
        if ( count == elems.length )
            resize(2*count);
        elems[tail++] = item;
        count++;
        if ( tail == elems.length ) tail = 0; // if parts in the back are filled, fill values to the front
    }

    public Item dequeue( )
    {
        if ( isEmpty() ) throw new NoSuchElementException();
        Item item = elems[head];
        elems[head] = null;
        count--;
        head++;
        if ( head == elems.length ) head = 0;
        if ( count > 0 && count == elems.length / 4 ) resize(elems.length/2);
        return item;
    }

    public Item sample()
    {
        int index = StdRandom.uniform(count);
        Item item = elems[(head+index)%elems.length];
        return item;
    }

    public Iterator<Item> iterator()
    {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item>
    {
        public int current;
        private int[] randSeries;
        public RandomizedQueueIterator()
        {
            current = 0;
            randSeries = StdRandom.permutation(count);
        }
        public boolean hasNext() { return current < count; };
        public void remove() { throw new UnsupportedOperationException(); };
        public Item next()
        {
            if ( !hasNext() ) throw new NoSuchElementException();
            Item item = elems[(randSeries[current]+head)%elems.length];
            current++;
            return item;
        }
    }

    public static void main(String[] args)
    {
        RandomizedQueue<Integer> r = new RandomizedQueue<>();
        for ( int i = 0; i < 10; i++ )
            r.enqueue(i);
        for ( int i : r )
            System.out.print(i);
    }
}
