import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item>
{
    public int capacity;
    public int count;
    public Item[] elems;

    // constructor
    public Stack ()
    {
        capacity = 0;
    }

    // constructor
    public Stack ( int size )
    {
        capacity = size;
        elems = (Item[]) new Object[capacity];
    }

    public isFull( ) { return count == capacity; };
    public isEmpty( ) { return count == 0; };

    public void push( Item item )
    {

    }


}

