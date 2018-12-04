import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue_LinkedList<Item> implements Iterable<Item>
{
    private class Node<Item>
    {
        public Item val;
        public Node<Item> next;

        public Node () { next = null; };
        public Node ( Item item ) { val = item; next = null; };
    }

    private class ListIterator<Item> implements Iterator<Item>
    {
        private Node<Item> current;

        public ListIterator ( Node<Item> node ) { current = node; };

        public boolean hasNext( )
        {
            return current != null;
        }

        public Item next( )
        {
            if ( !hasNext( ) )
                throw new NoSuchElementException();
            Node<Item> node = current;
            current = current.next;
            return node.val;
        }

    }

    public Node<Item> head, tail;

    // Constructor
    public Queue_LinkedList ( ) { head = tail = null; };

    // Iterator
    public Iterator<Item> iterator()
    {
        return new ListIterator<Item>( head );
    }

    // add elements to the end
    public void enqueue( Item elem )
    {
        Node<Item> n = new Node<>( elem );
        if ( head == null && tail == null )
        {
            head = n;
            tail = head;
        }
        tail.next = n;
        tail = tail.next;
    }

    // extract from the beginning
    public Item dequeue( )
    {
        if ( isEmpty() )
            throw new NoSuchElementException("Queue_LinkedList underflow");
        Node<Item> n = head;
        head = head.next;
        return n.val;
    }

    public boolean isEmpty()
    {
        return head == null;
    }

    public static void main(String[] args)
    {
        Queue_LinkedList<Integer> q = new Queue_LinkedList<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);

        for ( Integer i : q )
            System.out.println(i);

    }

}
