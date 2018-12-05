import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue_Array<Item> implements Iterable<Item>
{
    private Item[] elems;
    private int count;
    private int head,tail;

    // constructor
    public Queue_Array ( )
    {
        elems = (Item[]) new Object[2];
        head = 0;
        tail = 0;
        count = 0;
    }

    public boolean isEmpty( )
    {
        return count == 0;
    }

    public int size()
    {
        return count;
    }

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

    private class ArrayIterator implements Iterator<Item>
    {
        public int current;
        public ArrayIterator ( ) { current = 0; };
        public boolean hasNext() { return current < count; };
        public void remove() { throw new UnsupportedOperationException(); };
        public Item next()
        {
            if ( !hasNext() ) throw new NoSuchElementException();
            Item item = elems[(current+head)%elems.length];
            current++;
            return item;
        }
    }

    public Iterator<Item> iterator() { return new ArrayIterator(); };

    public static void main(String[] args)
    {
        Queue_Array<String> q = new Queue_Array<>();

        q.enqueue("I");
        q.enqueue("am");
        q.enqueue("tired");

        for ( String s : q )
            System.out.println(s);

    }
}
