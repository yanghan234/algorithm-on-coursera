import java.util.Iterator;
import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public class Deque<Item> implements Iterable<Item>
{
    private class Node<Item>
    {
        public Item val;
        public Node<Item> left, right;
        public Node( ) { left = right = null; };
    }

    private class DequeIterator<Item> implements Iterator<Item>
    {
        public Node<Item> current;
        public DequeIterator ( Node<Item> node ) { current = node; }

        public boolean hasNext()
        {
            return current != null;
        }

        public void remove( )
        {
            throw new UnsupportedOperationException();
        }

        public Item next()
        {
            if ( !hasNext() )
                throw new NoSuchElementException();
            Node<Item> node = current;
            current = current.right;
            return node.val;
        }
    }

    private Node<Item> head, tail;
    private int count;
    public Iterator<Item> iterator()
    {
        return new DequeIterator<Item>( head );
    }

    public Deque( )
    {
        head = tail = null;
        count = 0;
    }

    public boolean isEmpty( )
    {
        return head == null && tail == null;
    }

    public int size() { return count; };

    public void addFirst( Item item )
    {
        if ( item == null )
            throw new IllegalArgumentException("addFirst argument cannot be null");
        Node<Item> node = new Node<>();
        node.val = item;
        if ( head == null && tail == null )
        {
            head = tail = node;
        }
        else
        {
            head.left = node;
            node.right = head;
            head = head.left;
        }
        count++;
    }

    public void addLast( Item item )
    {
        if ( item == null )
            throw new IllegalArgumentException("addLast argument cannot be null");
        Node<Item> node = new Node<>();
        node.val = item;

        if ( head == null && tail == null )
        {
            head = tail = node;
        }
        else
        {
            tail.right = node;
            node.left = tail;
            tail = tail.right;
        }
        count++;
    }

    public Item removeFirst()
    {
        if ( isEmpty() )
            throw new NoSuchElementException("Deque is empty");

        Node<Item> node = head;
        head = head.right;
        count--;
        if ( head != null ) head.left = null;
        return node.val;
    }

    public Item removeLast()
    {
        if ( isEmpty() )
            throw new NoSuchElementException("Deque is empty");

        Node<Item> node = tail;
        tail = tail.left;
        count--;
        if ( tail != null ) tail.right = null;
        return node.val;
    }

    public static void main(String[] args)
    {
        Deque<Integer> q = new Deque<>();

        q.addFirst(2);
        q.addLast(1);
        q.addFirst(3);
        q.addLast(-1);

        // System.out.println(q.removeFirst());
        q.addLast(4);
        // System.out.println(q.removeLast());
        // System.out.println(q.removeFirst());
        // System.out.println(q.removeFirst());
        // System.out.println(q.removeFirst());
        //

        for ( int i : q )
            System.out.println(i);

    }
}
